var TFTP = require('tftp-client');


const tftp = require('tftp')

const client = tftp.createClient({
	host: "127.0.0.1",
  	port: 69
})

var options = {
	userExtensions: {
	  foo: "bar",
	  num: 2
	}
  };

  var get = client.createGetStream("text.txt");
  