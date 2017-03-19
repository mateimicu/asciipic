Titlu: AsciiPic
==============

Descriere:
---------

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

Generarea fisierului de configurare
-----------------------------------
```bash

# genetare a config file
~ asciipic/ $ oslo-config-generator --config-file etc/asciipic/asciipic-config-generator.conf
~ asciipic/ $ sudo mkdir /etc/asciipic/

# copy the config file in /etc/asciipic
~ asciipic/ $ sudo cp etc/asciipic/asciipic.conf.sample /etc/asciipic/asciipic.conf
```

Quick start
-----------
Este ingicata generarea unui fisier de configurare prima data.

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
~ asciipic/ $ virtualenv .venv/asciipic

# activate the venv
~ asciipic/ $ source .venv/asciipic/bin/activate

# install the project
~ asciipic/ $ pip install ../asciipic
# Or 
~ asciipic/ $ python setup.py install
```

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
