Student: Boldisor Dragos-Alexandru
Grupa: 322CB

			PROIECT POO

Arhiva contine:
- fisierele .java in folderul src
- fisierele cu teste (input, output) in folderul Teste
- JavaDoc-ul in folderul JavaDoc

Design Pattern-uri utilizate: Facade, Builder, Factory Method, Singleton.
Genericitate: Clasa Pair si metoda printActive din AuctionHouse.
Multithreading: Auction-urile implementeaza Runnable. In afara de cursul
normal in care se primesc comenzi, exista si threadurile pentru fiecare
licitatie.
Testarea aplicației cu minim 10 scenari (In folderul "Teste").
Folosirea conceptelor POO (incapsulare, mostenire, abstractizare, polimorfism).

Am folosit lambda expressions.


Proiectul simuleaza cu ajutorul inputurilor comportamentul unei Case de
Licitatii. In Clasa Main se afla metoda main care creaza un obiect de tip
facade, apoi creaza, cu ajutorul unui loop infinit, o consola care primeste
constant comenzi (la comanda quit sau exit se va opri programul).

Facade contine metode pentru fiecare comanda/functionalitate. Acesta apeleaza,
la randul lui, metodele din clasele care modeleaza comportamentul intregului
proces de licitatii.

Admin-ul poate adauga produse si poate muta produse din lista produselor
eligibile pentru licitatii in lista celor care se afla deja in procesul de
licitatie.

Broker-ul poate sterge produse dupa ce acestea au fost castigate. De asemenea,
el este cel care intermediaza procesul: cand este randul lui, va cere clientului
(prin metoda askForBid) suma pe care clientul doreste sa o plateasca. El este si
cel care notifica clientul ca a castigat si ii aplica comision (in functie de
situatiile prezentate in enunt).

Clientul, care este de doua feluri: Persoana fizica sau juridica este creat cu
Design Pattern-ul Factory Method. El decide cat sa ofere cand i se cere de catre
broker (generat cu Math.random()). De asemenea, el poate sa notifice Casa de
licitatii daca doreste sa liciteze pentru un produs.

Produsele sunt de 3 tipuri: Pictura, Bijuterie si Mobila. Produsele sunt
adaugate prin comanda addproduct si afisate prin printproducts. Sunt create cu
Design Pattern-ul Builder. Pentru diferentele de field-uri dintre cele 3 tipuri
se foloseste instanceof (in metodele withFirstArgument si withSecondArgument).

Auction House (implementat ca Singleton) detine listele cu brokeri, clienti,
produse si indeplineste cateva functionalitati: asociaza un broker unui client,
calculeaza maximul la fiecare runda de licitatii si afiseaza elementele active
din interiorul sau (brokeri, clienti, produse).

Pe langa aceste functionalitati mai avem: 5 Exception-uri care sunt apelate in
diferite situatii, 2 Enum-uri precum si clasa Pair. Clasa Pair este o clasa
generica folosita pentru a returna doua valori legate (un tuplu de doua valori).
In cazul nostru, metoda de calculare a maximului din Auction House va returna
un Pair de Broker, Double care reprezinta suma maxima din acea runda impreuna
cu broker-ul al carui client a oferit suma respectiva.