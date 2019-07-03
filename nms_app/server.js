// load the things we need
var express = require('express');
var app = express();


app.use(express.static(__dirname + '/'));
// set the view engine to ejs
app.set('view engine', 'ejs');

// use res.render to load up an ejs view file

// index page
app.get('/', function(req, res) {

		res.render('pages/index');

});

// about page
app.get('/about', function(req, res) {
	res.render('pages/about');
});

app.get('/performance', function(req, res) {
	res.render('pages/performance');
});

app.get('/fault', function(req, res) {
	res.render('pages/fault');
});
app.get('/signin', function(req, res) {
	res.render('pages/signin');
});

app.get('/gis', function(req, res) {
	res.render('pages/gis');
});
app.get('/users', function(req, res) {
	res.render('pages/users');
});
app.get('/signup', function(req, res) {
	res.render('pages/signup');
});

app.listen(8080);
console.log('8080 is the magic port');
