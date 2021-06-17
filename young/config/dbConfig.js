/*  DB Info */
module.exports = 
{
    user : process.env.NODE_ORACLEDB_USER || "young",
    password : process.env.NODE_ORACLEDB_PASSWOR || "1234",
    connectString : process.env.NODE_ORACLEDB_CONNECTIONSTRING || "211.54.188.77/xe"
}