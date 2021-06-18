var fs = require("fs");
var http = require("http");
var ejs = require("ejs");
var async = require("async");

var db = require("../../config/dbConfig.js").connect;

exports.listener=function(request, response){
	
	var sql = " select * from testTable \n"
	console.log("member_view sql ==", sql);
	
	db.query(sql, function(error, results){
		response.send({
            data : results
		});
	});
};
