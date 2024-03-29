Titlu: AsciiPic
==============
[![Requirements Status](https://requires.io/github/micumatei/ascipic/requirements.svg?branch=master)](https://requires.io/github/micumatei/ascipic/requirements/?branch=master)

### Descriere:


Inspirat din CoLiW. 
In mod normal, accesul la Web se realizeaza pe baza unui browser. 
Se doreste experimentarea unei interactiuni Web in linia de comandă, punandu-se la dispozitie o platforma 
extensibila capabila sa ofere suport pentru cautarea, manipularea, agregarea si exportarea imaginilor 
impreuna cu metadata ascociata acestora utilizand difierite API-uri publice ca Flickr, Instagram, etc.

Aplicatia va expune propriul API si va pune la dispozitie doua modalitati de interactiune cu acesta: 
  - una prin intermediul unei interfete web ce emuleaza o linie de comanda
  - iar a doua folosind un client, direct dintr-un terminal.

Printre functionalitati aplicatia permite:
  - cautarea si filtrarea imaginilor dupa tag-uri, data postarii, dimensiune etc.
  - vizualizarea imaginilor in format ascii(atat la linia de comanda cat si in interfata web);
  - exportarea url-urilor impreuna cu metadata asociata acestora in diverse formate(JSON, XML, etc.);
  - transformarea imaginilor astfel gasite(resize, aplicarea diverselor filtre);

Datorita faptului ca anumite request-uri pot fi costisitoare oferim posibilitatea postarii asincrone de job-uri 
care for fi executate de o arhitectura distribuita de work-eri.
Astfel putem interoga statusul job-ului sau la finalul lui putem opta pentru primirea unui e-mail cu informatiile cerute.


Am estimat proiectul ca fiind unul de tip S care ar avea nevoie de 4 persoane pentru o implementare optima in timpul acordat.

---

Website: https://asciipic.xyz/


Quick start
-----------
Este indicata generarea unui fisier de configurare prima data.

```bash
# install deps
# for ubuntu
~ $ sudo apt-get install vim git python-dev -y
# fot centos
~ $ sudo yum install vim git python-dev -y

~ $ pip install virtualenv

# clone project
~ $ git clone https://github.com/micumatei/asciipic
~ $ cd asciipic

# create a virtual env for this project
~ asciipic/ $ virtualenv --python=python3 .venv/asciipic

# activate the venv
~ asciipic/ $ source .venv/asciipic/bin/activate

# install the project
~ asciipic/ $ pip install ../asciipic
# Or 
~ asciipic/ $ python setup.py install

# install oracle client
# Se poate sa aveti nevoie de niste binare de la oracle :'(
# dar daca aveti oracle-db instalat pe masina ar trebui sa fie deja instalate
~ asciipic/ $ pip install -r oracle-requirements.txt

```

Generarea fisierului de configurare
-----------------------------------
Pentru a genera fisierul de configurare trebuie sa avem proiectul deja instalat.
```bash

# genetare a config file
~ asciipic/ $ oslo-config-generator --config-file etc/asciipic/asciipic-config-generator.conf
~ asciipic/ $ sudo mkdir /etc/asciipic/

# copy the config file in /etc/asciipic
~ asciipic/ $ sudo cp etc/asciipic/asciipic.conf.sample /etc/asciipic/asciipic.conf
```


Requirements
-----------
Proiectul are cateva dependite mai speciale:
####OracleDB
Foloseste ca DB oracle si trebuie sa specificam informatiile de acces catre aceasta baza de date
in fisierul `/etc/asciipic/asciipic.conf`.
Exemplu :
```
[oracle]
host = x.x.x.x
port = 8080
username = ASCIIPIC
password = ASCIIPIC
```
Pentru a instala oracle puteti urmari acest [tutorial](http://www.oracle.com/webfolder/technetwork/tutorials/obe/db/11g/r1/prod/install/dbinst/dbinst.htm) dar din experienta procesul nu este prea usor.
O varianta mai usoara este sa folosim docker-engine pentru a porni un container cu oracle deja instalat.
#####OracleDB in docker container 
Pentru a instala docker puteti urmari [documentatia oficiala](https://docs.docker.com/engine/installation/).
Un tutorial bun pentru python se poate gasi [aici](https://gist.github.com/kimus/10012910).
Dupa ce aveti docker instalat puteti porni containerul ( creat special pentru aceasta aplicatie ) cu urmatoare comanda:
` docker run --name oracle-asciipic -d -p 8081:1521 matei10/asciipic_db:latest`

####Redis
Folosit pentru short-term storage si channels ( messaging queue ). 
La fel ca in cazul bazei de date trebuie sa specificam datele de acces in fisierul `/etc/asciipic/asciipic.conf`:
```
[redis]
host = 127.0.0.1
port = 6379
database = 0
```

##Heroku
Pentru CD folosim [Heroku](http://herokuapp.com/) in care trebuie sa instalam clientul nativ de oracle.
Cea mai sipla varianta e sa folosim doua buildpacks:
- la indexul 1 `https://github.com/wealthsimple/oracle-heroku-buildpack`
- la indexul 2 `heroku/python`

Primul va instala clientul nativ pentru oracle al doilea va instala proiectul de python

**Note** Putem folosi si buildpack-ul `multi-builpack` pentru a versiona pachetele


Test the project
----------------
Dupa ce s-a intalat proiectul putem sa il testam pronind local API-ul.

```bash
# activate the venv
~ asciipic/ $ source .venv/asciipic/bin/activate

# start the api
~ asciipic/ $ asciipic server start
[19/Mar/2017:02:06:35] ENGINE Listening for SIGHUP.
[19/Mar/2017:02:06:35] ENGINE Listening for SIGHUP.
[19/Mar/2017:02:06:35] ENGINE Listening for SIGTERM.
[19/Mar/2017:02:06:35] ENGINE Listening for SIGTERM.
[19/Mar/2017:02:06:35] ENGINE Listening for SIGUSR1.
[19/Mar/2017:02:06:35] ENGINE Listening for SIGUSR1.
[19/Mar/2017:02:06:35] ENGINE Bus STARTING
[19/Mar/2017:02:06:35] ENGINE Bus STARTING
....
....
....
[19/Mar/2017:02:06:35] ENGINE Serving on http://127.0.0.1:8080
[19/Mar/2017:02:06:35] ENGINE Bus STARTED
[19/Mar/2017:02:06:35] ENGINE Bus STARTED
....
....
....
```
Putem verifica acum la url-ul afisat(va depinde de fisierul de configurare) daca avem acces la api 

Producer / Consumer example
---------------------------
Intr-un terminal rulati `asciipic worker start` pentru a porni un worker

In alt terminal rulati task-ul exemplu
```
from rq import Queue

from asciipic.worker import worker
from asciipic.tasks import example_task

# NOTE(mmicu): just a hack in order to get
# the rededis connection
w = worker.Worker()
rc = w.rcon

q = Queue(connection=rc , name="low")
r = q.enqueue(example_task.ExampleTask())
```
