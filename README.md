# Inicijalne upute za prijavu projekta iz Razvoja aplikacija za mobilne i pametne uređaje

Poštovane kolegice i kolege, 

čestitamo vam jer ste uspješno prijavili svoj projektni tim na kolegiju Razvoj aplikacija za mobilne i pametne uređaje, te je za vas automatski kreiran repozitorij koji ćete koristiti za verzioniranje vašega koda i za jednostavno dokumentiranje istoga.

Ovaj dokument (README.md) predstavlja **osobnu iskaznicu vašeg projekta**. Vaš prvi zadatak je **prijaviti vlastiti projektni prijedlog** na način da ćete prijavu vašeg projekta, sukladno uputama danim u ovom tekstu, napisati upravo u ovaj dokument, umjesto ovoga teksta.

Za upute o sintaksi koju možete koristiti u ovom dokumentu i kod pisanje vaše projektne dokumentacije pogledajte [ovaj link](https://guides.github.com/features/mastering-markdown/).
Sav programski kod potrebno je verzionirati u glavnoj **master** grani i **obvezno** smjestiti u mapu Software. Sve artefakte (npr. slike) koje ćete koristiti u vašoj dokumentaciju obvezno verzionirati u posebnoj grani koja je već kreirana i koja se naziva **master-docs** i smjestiti u mapu Documentation.

Nakon vaše prijave bit će vam dodijeljen mentor s kojim ćete tijekom semestra raditi na ovom projektu. Mentor će vam slati povratne informacije kroz sekciju Discussions također dostupnu na GitHubu vašeg projekta. A sada, vrijeme je da prijavite vaš projekt. Za prijavu vašeg projektnog prijedloga molimo vas koristite **predložak** koji je naveden u nastavku, a započnite tako da kliknete na *olovku* u desnom gornjem kutu ovoga dokumenta :) 

# Scriptify
"Scriptify: Where Every Page Finds its Perfect Reader!"

## Projektni tim
(svi članovi tima moraju biti iz iste seminarske grupe)

Ime i prezime | E-mail adresa (FOI) | JMBAG | Github korisničko ime | Seminarska grupa
------------  | ------------------- | ----- | --------------------- | ----------------
Ime i prezime | mojaadresa@foi.hr | 000000000 | githubuser | G01
David Škudar | dskudar20@foi.hr | 0016147892 | dskudar20 | G02
Marino Štura | mstura21@student.foi.hr | 0016153181 | dskudar20 | G02

## Opis domene
Domena aplikacije za prodaju knjiga Scriptify obuhvaća stvaranje digitalne platforme koja omogućuje korisnicima kupovinu knjiga, kao i knjižarama mogućnost na prodaju svojih knjiga. Ova aplikacija treba biti korisniku prijateljska, sigurna i pouzdana, istovremeno pružajući autenticiranu i raznovrsnu kolekciju knjiga. Glavni problem koji se rješava ovom aplikacijom je olakšavanje procesa pronalaženja, kupovine knjiga za kupca, a za knjižare prodaje knjiga. 
![ramp drawio](https://github.com/foivz/rampu23-scriptify/assets/126589951/a57ec2cb-54c7-4574-ae7b-2be8824575ce)
Slika.1.1
Na slici se prikazuje domena i sami ciklus aplikacije u cijelosti.


## Specifikacija projekta

Oznaka | Naziv | Kratki opis | Odgovorni član tima
------ | ----- | ----------- | -------------------
F01 | Registracija | Odvajamo dva registracijska ekrana, jedan za korisnika i drugi za knjižare. Korisnici bi trebali dati svoje privatne podatke kao što su Korisničko ime, prezime, email i adresa. Dok knjižare bi trebale dati email, naziv, adresa, porezni broj, oib vlasnika, kontakt vlasnika. Svi podaci se pišu u bazi podataka gdje su sigurno čuvani. Po tipu korisnika on dobiva prava.  | Zlatko Stapić
F02 | Login | Nakon registracije, korisnik ili knjižara sljedeći put se mogu logirati, njihovi podaci su upisani u sustav. Oboje moraju kliknuti na gumb korisnika ili knjižare. Ovisno o tome korisnik se može ulogirati i koristiti aplikaciju. U slučaju da Korisnik pokuša logirati kao knjižara sustav mu to ne bi omogućio.  | ...
F03 | Login |   | ...

## Tehnologije i oprema
Implementaciju naše aplikacije radit ćemo u programskom jeziku Kotlin te koristiti Android Studio razvojno okruženje. Za verzioniranje programskog koda koristit ćemo Git i GitHub. Kako bi pratili razvoj naše aplikacije pisat ćemo jednostavnu dokumentaciju u Github Wiki, a projektne zadatke ćemo planirati i pratiti u alatu GitHub projects.

## Baza podataka i web server
Nastavnici vam mogu pripremiti MySQL bazu podataka i web server na kojem možete postaviti jednostavne php web servise. Ako želite da vam pripremimo ove sustave obavezno to navedite umjesto ovog teksta s napomenom "Trebamo bazu podataka i pristup serveru za PHP skripte". Alternativno, možete koristiti bilo koji online dostupan sustav kao i studentske licence na pojedinim platformama kao što su Heroku ili Azure.

## .gitignore
Uzmite u obzir da je u mapi Software .gitignore konfiguriran za nekoliko tehnologija, ali samo ako će projekti biti smješteni direktno u mapu Software ali ne i u neku pod mapu. Nakon odabira konačne tehnologije i projekta obavezno dopunite/premjestite gitignore kako bi vaš projekt zadovoljavao kriterije koji su opisani u ReadMe.md dokumentu dostupnom u mapi Software.
