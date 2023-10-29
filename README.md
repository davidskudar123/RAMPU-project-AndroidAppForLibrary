
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
F01 | Registracija |Omogućuje korisnicima da se registriraju i prijave na svoj korisnički račun. Nakon prijave, korisnici imaju pristup funkcijama aplikacije. Korisnici se registriraju korisničkim imenom,lozinkom,emailom,imenom, prezimenom i adresom. Svi korisnici će moći naknadno dodavati svoje knjige na platformu razmjene.  | Luka Nižić 
F02 | Login | Nakon registracije, korisnik sljedeći put se može logirati, njihovi podaci su upisani u sustav. Login sustav će zatražiti korisničko ime i lozinku.  | Marino Štura
F03 | Menadžment knjiga  | Korisnici koji su se uspješno registrirale i logirale imaju opciju na svoj market dodavati, mijenjati, brisati knjige. Prilikom dodavanja otvara se ekran gdje korisnik stavlja sliku knjige, ime, kratki opis, žanr, prosječno vrijeme čitanja, oznaku Scriptify Aproved. Nakon dodavanja knjige korisnik može opet ući u knjigu da promjeni bilo koju postavku. Sva ažuriranja i brisanja se također dešavaju i na bazi podataka.  | Marino Štura
F04 | Korisnički profil | Korisnici mogu uređivati svoj profil, dodavati informacije o sebi i profilnu sliku. Prikazuje informacije o korisniku kao što su ime, slika profila, broj posuđenih knjiga , nakon 10 uspješno prodanih knjiga drugim korisnicima, korisnik ulazi u Scriptify Aproved program. Korisnici imaju novčanik u koji mogu dodavati novce i plaćati knjige drugih korisnika.  | David Škudar
F05 | Pregled knjiga | Nakon što je korisnik se ulogirao, vidi popis svih knjižnica u sustavu, na knjižnice može kliknuti i vidjeti koje sve knjige posuđuju. Klikom na knjigu korisnik treba vidjeti sve informacije o knjizi koju posuđuje. Uz to ima i pristup knjigama koje je su stavili drugi korisnici na market. | Luka Nižić 
F06 | Upravljanje posudbe knjižnice | Knjižnice mogu vidjeti korisnike koji su posudili knjige i njihove adrese kako bi mogli slati korisnicima posuđene knjige. Knjižnice u opciji posudbe mogu vidjeti sve knjige koje su naručene od određenih korisnika i status jesu li knjige isporučene. Mogućnost odvajanja isporučenih od neisporučenih knjiga. Nakon što je knjiga isporučena period posudbe kreće. Sa posuđenim knjigama korisnik ne može trgovati.  |Luka Nižić 
F07 | Razmjena knjiga između korisnika | Omogućuje korisnicima da kupuju knjigu na korisničkom marketplace-u nakon što korisnik klikne na gumb kupi otvara se opis knjige koje kupuje i način plaćanja. Svaka kupnja je odvojena i svaku knjigu korisnik može platiti ili gotovinom pouzećem ili novčanikom unutar aplikacije. Korisnik koji prodaje knjigu ima obavijest da je knjiga kupljena i može početi proces dostave. Može i razmjeniti knjige na gumb razmjeni gdje dodaje knjigu sa svoje police koju želi zamjeniti, korisnika se obaviještava i u tabu requests korisnik može odobriti ili ugasiti zahtjev za razmjenom.|David Škudar
F08 | Primitak knjige | Korisnik u narudžbama ima knjigu koju je naručio i status je pending, sve dok korisnik ne dobije knjigu. Kada služba za dostavljanje dostavi korisniku knjigu, korisnik je dužan obavijestiti da je knjigu prihvatio. Pritiskom na gumb obavljeno, narudžba će biti obavljena. Ova opcija se nalazi u received books.|David Škudar
F09 |Recenzije i Ocjene  | Omogućuje korisnicima da ocjenjuju i recenziraju knjige od knjižnica kao i korisnike pri razmjeni knjiga. Agregiranje ocjena i recenzija kako bi korisnicima pomoglo u donošenju informiranih odluka. |Marino Štura |
F10 |Scriptify Aproved program  | Korisnici koji prodali drugim korisnicima 10 ili više knjiga postaju Scriptify Aproved, što znači da su pouzdani korisnici. Oni će se prvi drugim korisnicima pri razmjeni knjiga. | David Škudar,Luka Nižić,Marino Štura

## Tehnologije i oprema
Implementaciju naše aplikacije radit ćemo u programskom jeziku Kotlin te koristiti Android Studio razvojno okruženje. Za verzioniranje programskog koda koristit ćemo Git i GitHub. Kako bi pratili razvoj naše aplikacije pisat ćemo jednostavnu dokumentaciju u Github Wiki, a projektne zadatke ćemo planirati i pratiti u alatu GitHub projects.

## Baza podataka i web server
Prema prijedlogu, koristit ćemo lokalno podignut server (express.js).

## .gitignore
Uzmite u obzir da je u mapi Software .gitignore konfiguriran za nekoliko tehnologija, ali samo ako će projekti biti smješteni direktno u mapu Software ali ne i u neku pod mapu. Nakon odabira konačne tehnologije i projekta obavezno dopunite/premjestite gitignore kako bi vaš projekt zadovoljavao kriterije koji su opisani u ReadMe.md dokumentu dostupnom u mapi Software.
