const mysql = require('mysql');
const express = require('express');
const app = express();
app.use(express.json());
let con = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '1234',
  database: 'Scriptify_final', // ZAMJENI IME PO POTREBI
  port: 3306
});

app.get('/', (req, res) => {


    console.log('Connected to database');


    con.query('SELECT * FROM user', (error, results) => {
      if (error) {
        console.error('Error querying database: ' + error.stack);
        res.status(500).json({ error: 'Error querying database' });
        return;
      }


      res.json(results);



    });
  });

app.get('/loginuser/:id', (req, res) => {

  const userId = req.params.id;
    console.log('Connected to database');

    // Insert user data into the database
    const sql = `SELECT * FROM user WHERE id_user = '${userId}';`
    con.query(sql, (error, results, fields) => {
      if (error) {
        console.error('Error inserting data: ' + error.stack);
        res.status(500).json({ error: 'Error inserting data' });
        return;
      }



      // Sending a success response
      res.json(results);

      // Don't forget to end the connection after the query is executed

    });
  });

app.post('/updateUserData/:id', (req, res) => {
  const userId = req.params.id;
  const userData = req.body;  // Podaci o korisniku koji se šalju putem JSON-a

  console.log('Connected to database');

  // Izvrši SQL UPDATE upit
  const sql = `UPDATE user SET first_name = ?, last_name = ?, address = ?, username = ?, email = ?, password = ? WHERE id_user = ?`;
  con.query(sql, [userData.first_name, userData.last_name, userData.address, userData.username, userData.email, userData.password, userId], (error, results, fields) => {
    if (error) {
      console.error('Error updating data: ' + error.stack);
      res.status(500).json({ error: 'Error updating data' });
      return;
    }

    // Slanje odgovora o uspješnom ažuriranju
    res.json({ message: 'Data updated successfully' });
  });
});

app.post('/updateUserMoney/:id', (req, res) => {
  const userId = req.params.id;
  const userData = req.body;  // Podaci o korisniku koji se šalju putem JSON-a

  console.log('Connected to database');

  // Izvrši SQL UPDATE upit
  const sql = `UPDATE user SET money = ? WHERE id_user = ?`;
  con.query(sql, [userData.money, userId], (error, results, fields) => {
    if (error) {
      console.error('Error updating data: ' + error.stack);
      res.status(500).json({ error: 'Error updating data' });
      return;
    }

    // Slanje odgovora o uspješnom ažuriranju
    res.json({ message: 'Data updated successfully' });
  });
});

app.get('/myBooks/:id',(req,res)=>{
   const userId = req.params.id
   const sql = `SELECT b.*
   FROM Knjige b
   JOIN user_has_Knjige u
     ON b.idKnjige = u.Knjige_idKnjige
   WHERE u.user_id_user = ${userId};`
    con.query(sql, (error, results, fields) => {
      if (error) {
        console.error('Error inserting data: ' + error.stack);
        res.status(500).json({ error: 'Error inserting data' });
        return;
      }
      // Sending a success response
      res.json(results);

      // Don't forget to end the connection after the query is executed

    });
})

app.get('/deleteBook/:id',(req,res)=>{
  const bookId = req.params.id
  const sql = `DELETE FROM user_has_Knjige WHERE Knjige_idKnjige = ${bookId};`
  con.query(sql,(err,results,fields)=>{
      if(err){
        console.log(err)
        res.status(500).json({error:'Error deleting'})
        return
      }
      res.json(results)
  })
})
app.post('/userBookCon',(req,res)=>{
    const userBook = req.body
    const sql = "INSERT INTO user_has_Knjige (user_id_user,Knjige_idKnjige) values (?,?)"
    con.query(sql,[userBook.user_id_user,userBook.Knjige_idKnjige],(err,results,fields)=>{
      if(err){
        console.log(err)
        res.status(500).json({error:"Book adding error"})
      }
    })
    res.json({success:"Book added"})
})
app.post('/makeBook',(req,res)=>{
    const bookData = req.body
    console.log('Book has been made')
    const sql = "INSERT INTO KNJIGE (idKnjige, naziv_knjige, autor, Description) values (?,?,?,?);"
    con.query(sql,[bookData.idKnjige,bookData.naziv_knjige,bookData.autor,bookData.Description],(err,results,fields)=> {
      if(err){
        console.log(err)
        res.status(500).json({error:"Book adding error"})
        return
      }
      res.json({message:"Book has been added"})
    })
})
// Update book details route
app.post('/updateBook/:id', (req, res) => {
   const userId = req.params.id;
  const userData = req.body;  // Podaci o korisniku koji se šalju putem JSON-a

  console.log('Connected to database');

  // Izvrši SQL UPDATE upit
  const sql = `UPDATE Knjige
  SET naziv_knjige = ?, Description = ?, autor = ?
  WHERE idKnjige = ?;`;
  con.query(sql, [userData.naziv_knjige, userData.Description, userData.autor, userId], (error, results, fields) => {
    if (error) {
      console.error('Error updating data: ' + error.stack);
      res.status(500).json({ error: 'Error updating data' });
      return;
    }

    // Slanje odgovora o uspješnom ažuriranju
    res.json({ message: 'Data updated successfully' });
  });
});
 app.post('/register', (req, res) => {
    const userData = req.body;  // User data sent via JSON

    console.log('Connected to database');

    // Insert user data into the database
    const sql = `
        INSERT INTO user (username, password, email, address, first_name, last_name)
        VALUES (?, ?, ?, ?, ?, ?);
    `;

    con.query(
        sql,
        [
            userData.username,
            userData.password,
            userData.email,
            userData.address,
            userData.first_name,
            userData.last_name
        ],
        (error, results, fields) => {
            if (error) {
                console.error('Error inserting data: ' + error.stack);
                res.status(500).json({ error: 'Error inserting data' });
                return;
            }

            // Sending a success response
            res.json({ message: 'Registration successful' });
        }
    );
});
app.listen(4000, () => {
  console.log('Listening on port 4000');
  con.connect((err) => {
    if (err) {
      console.error('Error connecting to database: ' + err.stack);
      res.status(500).json({ error: 'Error connecting to database' });
      return;
    }
});
})