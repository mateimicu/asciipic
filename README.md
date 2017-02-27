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
