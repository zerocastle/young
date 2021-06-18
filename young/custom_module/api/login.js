var oracledb = require('oracledb');
var fs = require("fs");
var http = require("http");
var ejs = require("ejs");
var async = require("async");

var db = require("../../config/dbConfig.js");

exports.listener=function(request, response){
	
	var sql = " select * from testTable \n"
	console.log("member_view sql ==", sql);
	// oracledb.getConnection(db.mysqlConfig);
	// db.execute(sql, function(error, results){
	// 	response.send({
    //         data : results
	// 	});
	// });


    oracledb.getConnection(db,
    function(err, connection) {
        if (err) {
            console.error(err.message);
            return;
        }

        let query = 'select * from testTable';

        connection.execute(query, [], function (err, result) {
            if (err) {
                console.error(err.message);
                doRelease(connection);
                return;
            }
            console.log(result.rows);                   // 데이터
            doRelease(connection, result.rows);         // Connection 해제
        });
    });    

    // DB 연결 해제
    function doRelease(connection, rowList) {
        connection.release(function (err) {
            if (err) {
                console.error(err.message);
            }

            // DB종료까지 모두 완료되었을 시 응답 데이터 반환
            console.log('list size: ' + rowList.length);
            
            response.send(rowList);
        });
    }




};
