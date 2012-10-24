
################################################################################
#################### Proiect 1: Document Processing Manager ####################
################################################################################

	Scopul acestui proiect este de a crea un executabil care sa realizeze 
managementul unui workflow complet al unei procesari, pornind de la imaginea
color si obtinand PDF-uri bazate pe imaginile selectate.


	Tipurile de executabile pentru care le poate utiliza aplicatia si ordinea
lor este urmatoarea:

	- preprocessing
	- binarization
	- layout
	- paging
	- OCR
	- hierarchy
	- PDF-exporter
	
	
	Workflow-ul tipic este urmatorul (complet):
	
	- utilizatorul are posibilitatea de a selecta unul sau mai multe fisiere
ale caror nume sunt conforme cu o expresie regulata sau care au fost selectate
individual prin explorarea ierarhiei de fisiere (printr-un window browser)

	- pentru fiecare tip de procesare, utilizatorului si se ofera posibilitatea 
de a alege executabile care au acel tip; nu pot exista selectate la un moment 
dat doua executabile care indeplinesc aceeasi functie, dar pot exista mai multe 
executabile care desi au acelasi tip, nu reprezinta alternativa unul pentru 
celalalt; de exemplu, se poate selecta un singur executabil ce are atat tipul
preprocessing si rotate, dar este acceptata selectia concomitenta a unui
executabil de tip preprocessing si blur si a unuia cu tipurile preprocessing si
rotate

	- este important de specificat ca intre primul si ultimul tip de procesare 
setat nu trebuie sa existe "pasi" lipsa; de exemplu, daca s-a selectat un
utilitar de tip binarization la inceput si ultimul este un utilitar de tip
paging, trebuie sa fi fost setate si executabile de tip layout si respectiv
hierarchy

	- pentru fiecare executabil selectat, se va oferi posibilitatea specificarii
parametrilor de rulare, conform schemei asociate executabilului

	- la finalul selectiei, se va realiza executia propriu-zisa dupa cum
urmeaza:

		1. fiecare executabil selectat va fi rulat pe rezultatul rularii
	executabilului anterior
	
		2. daca executabilul nu prelucreaza decat un fisier la fiecare rulare,
	atunci executabilul va fi rulat pentru fiecare fisier in parte	
	
		3. daca executabilul prelucreaza mai multe fisiere la o rulare, atunci 
	toate fisierele obtinute in faza anterioara vor fi adaugate la fisierul XML
	de input pentru prelucrare
	
		4. rularea consta in constructia fisierului XML in conformitate cu
	schema XML pe baza fisierelor disponibile (dupa cum s-a precizat la 2 si 3)
	si a parametrilor setati manual, urmata de executia prorpriu-zisa si 
	inspectia fisierului XML de raport
	
		5. pe baza rezultatelor din cadrul fisierului XML de raport se va decide
	daca se continua executia cu celelalte etape sau nu (daca sa prezinte un 
	mesaj de eroare sau nu)
	