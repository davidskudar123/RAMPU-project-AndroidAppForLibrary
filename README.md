
# Scriptify
"Scriptify: Where Every Page Finds its Perfect Reader!"

## Projektni tim
(svi članovi tima moraju biti iz iste seminarske grupe)

Ime i prezime | E-mail adresa (FOI) | JMBAG | Github korisničko ime | Seminarska grupa
------------  | ------------------- | ----- | --------------------- | ----------------
Luka Nižić | lnizic21@foi.hr | 0016155331 | lnizic21 | G02
David Škudar | dskudar20@foi.hr | 0016147892 | dskudar20 | G02
Marino Štura | mstura21@student.foi.hr | 0016153181 | mstura21-FOI | G02

## Opis domene
Domena aplikacije za posudbu i razmjenu knjiga Scriptify obuhvaća stvaranje digitalne platforme koja omogućuje korisnicima razmjenu knjiga i posudba knjiga od knjižnica. Ova aplikacija treba biti korisniku prijateljska, sigurna i pouzdana, istovremeno pružajući autenticiranu i raznovrsnu kolekciju knjiga. Glavni problem koji se rješava ovom aplikacijom je olakšavanje procesa pronalaženja, razmjene ili posudbe knjiga za kupca. 

## Specifikacija projekta

Oznaka | Naziv | Kratki opis | Odgovorni član tima
------ | ----- | ----------- | -------------------
F01 | Registracija | Odvajamo dva registracijska ekrana, jedan za korisnika i drugi za knjižare. Korisnici bi trebali dati svoje privatne podatke kao što su ime, prezime, email i adresa. Dok knjižare bi trebale dati email, naziv, adresa, porezni broj, oib vlasnika, kontakt vlasnika. Svi podaci se pišu u bazi podataka gdje su sigurno čuvani. Po tipu korisnika on dobiva prava.  | ...
F02 | Login | Nakon registracije, korisnik ili knjižara sljedeći put se mogu logirati, njihovi podaci su upisani u sustav. Oboje moraju kliknuti na gumb korisnika ili knjižare. Ovisno o tome korisnik se može ulogirati i koristiti aplikaciju. U slučaju da se korisnik pokuša logirati kao knjižara sustav mu to ne bi omogućio.  | ...
F03 | Menadžment knjiga  | Knjižare koje su se uspješno registrirale i logirale imaju opciju na svoj market dodavati, mijenjati, brisati knjige. Prilikom dodavanja otvara se ekran gdje knjižara stavlja sliku knjige, ime, kratki opis, žanr, prosječno vrijeme čitanja, oznaku Scriptify Aproved i cijenu knjige. Nakon dodavanja knjige korisnik može opet ući u knjigu da promjeni bilo koju postavku. Sva ažuriranja i brisanja se također dešavaju i na bazi podataka.  | ...
F04 | Korisnički novčanik | Korisnik može dodavati novac svom Scriptify Wallet-u. Nakon što je dodao novce ne može ih vratiti natrag. Korisnik može dodati novac u izborniku gdje specificira koliko novaca želi staviti na račun, a vanjski servisi se pobrinu da taj novac sigurno stigne u njegov Scriptify Wallet. Korisnik novčanikom može kupovati knjige. Plaćati posebne subskripcije aplikaciji.  | ...
F05 | Pregled knjiga | Nakon što je korisnik se ulogirao, vidi popis svih knjižara u sustavu, na knjižare može kliknuti i vidjeti koje sve knjige prodaju. Klikom na knjigu korisnik treba vidjeti sve informacije o knjizi koju kupuje.  | ...
F06 | Upravljanje kupnja knjižare | Knjižare mogu vidjeti korisnike koji su naručili knjige i njihove adrese kako bi mogli slati korisnicima naručene knjige. Knjižare u opciji narudžbe mogu vidjeti sve knjige koje su naručene od određenih korisnika i status jesu li knjige isporučene. Mogućnost odvajanja isporučenih od neisporučenih knjiga. |
F07 | Kupnja knjiga | Omogućuje korisnicima da kupuju knjigu nakon što korisnik klikne na gumb kupi otvara se opis knjige koje kupuje i način plaćanja. Svaka kupnja je odvojena i svaku knjigu korisnik može platiti ili gotovinom pouzećem ili Scriptify Wallet-om|
F08 | Primitak knjige | Korisnik u narudžbama ima knjigu koju je naručio i status je pending, sve dok korisnik ne dobije knjigu. Kada služba za dostavljanje dostavi korisniku knjigu, korisnik je dužan obavijestiti da je knjigu prihvatio. Pritiskom na gumb obavljeno, narudžba će biti obavljena.|
F09 |Recenzije i Ocjene  | Omogućuje korisnicima da ocjenjuju i recenziraju knjige. Agregiranje ocjena i recenzija kako bi korisnicima pomoglo u donošenju informiranih odluka. |
F10 |Scriptify Aproved program  | Korisnici koji imaju Scriptify wallet, mogu platiti one-time cijenu za posebne ponude knjiga, knjige koje tek dolaze ili koje su tek najavljane. Knjižare imaju opciju za svaku knjigu staviti Scriptify Aprooved tag gdje u tom slučaju samo korisnici iz ovog programa mogu čitati knjigu. Za druge korisnike koji nisu platili knjiga se neće pojaviti. |

## Tehnologije i oprema
Implementaciju naše aplikacije radit ćemo u programskom jeziku Kotlin te koristiti Android Studio razvojno okruženje. Za verzioniranje programskog koda koristit ćemo Git i GitHub. Kako bi pratili razvoj naše aplikacije pisat ćemo jednostavnu dokumentaciju u Github Wiki, a projektne zadatke ćemo planirati i pratiti u alatu GitHub projects.

## Baza podataka i web server
Prema prijedlogu, koristit ćemo lokalno podignut server (express.js).

## .gitignore
Uzmite u obzir da je u mapi Software .gitignore konfiguriran za nekoliko tehnologija, ali samo ako će projekti biti smješteni direktno u mapu Software ali ne i u neku pod mapu. Nakon odabira konačne tehnologije i projekta obavezno dopunite/premjestite gitignore kako bi vaš projekt zadovoljavao kriterije koji su opisani u ReadMe.md dokumentu dostupnom u mapi Software.
