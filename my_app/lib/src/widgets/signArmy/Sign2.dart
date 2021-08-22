import 'package:flutter/material.dart';

class Sign2 extends StatefulWidget {
  const Sign2({ Key? key }) : super(key: key);

  @override
  _Sign2State createState() => _Sign2State();
}

class _Sign2State extends State<Sign2> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Column(
            // mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text("회원정보", style: TextStyle(fontSize: 30, fontWeight: FontWeight.w700),),
              Text("기본정보를 입력해주세요.", style: TextStyle(fontSize: 15, fontWeight: FontWeight.w700, color: Colors.grey),),
              SizedBox(height: 40,),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Expanded(
                    flex: 1,
                    child: ElevatedButton(
                      onPressed: () => {},
                      child: Text('군인'),
                      style: ElevatedButton.styleFrom(
                        fixedSize: Size.fromHeight(50),
                        elevation: 0.0,
                        primary: Colors.black,
                        onPrimary: Colors.white,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.zero,
                            side: BorderSide(color: Colors.grey)
                        ),
                  
                      ),
                    ),
                  ),
                   Expanded(
                     flex: 1,
                     child: ElevatedButton(
                      onPressed: () => {},
                      child: Text('지인'),
                      style: ElevatedButton.styleFrom(
                        fixedSize: Size.fromHeight(50),
                        elevation: 0.0,
                        primary: Colors.white,
                        onPrimary: Colors.black,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.zero,
                            side: BorderSide(color: Colors.grey)
                        ),
                   
                      ),
                                     ),
                   ),
                   Expanded(
                     flex: 1,
                     child: ElevatedButton(
                      onPressed: () => {},
                      child: Text('업체'),
                      style: ElevatedButton.styleFrom(
                        fixedSize: Size.fromHeight(50),
                        elevation: 0.0,
                        primary: Colors.white,
                        onPrimary: Colors.black,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.zero,
                            side: BorderSide(color: Colors.grey)
                        ),
                   
                      ),
                                     ),
                   ),
                ],
              ),
              SizedBox(
                height: 20,
              ),
              Container(
                        child: 
                        TextField(
                        textInputAction: TextInputAction.go,
                        textAlign: TextAlign.start,
                        style: TextStyle(
                          color: Colors.black, 
                          fontSize: 16.0,
                          ),
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          labelText: "이름을 입력하세요.",
                          contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                          ),
                        ),
                  
                      decoration: BoxDecoration(
                        //  color: Colors.grey[300],
                        border: Border.all(
                          width: 1,
                          color: Colors.grey
                        )
                      ),
              ),
              SizedBox(
                height: 20,
              ),
              Container(
                        child: 
                        TextField(
                        textInputAction: TextInputAction.go,
                        textAlign: TextAlign.start,
                        style: TextStyle(
                          color: Colors.black, 
                          fontSize: 16.0,
                          ),
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          labelText: "이메일을 입력하세요.",
                          contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                          ),
                        ),
                  
                      decoration: BoxDecoration(
                        //  color: Colors.grey[300],
                        border: Border.all(
                          width: 1,
                          color: Colors.grey
                        )
                      ),
              ),
              SizedBox(
                height: 20,
              ),
              Container(
                        child: 
                        TextField(
                        textInputAction: TextInputAction.go,
                        textAlign: TextAlign.start,
                        style: TextStyle(
                          color: Colors.black, 
                          fontSize: 16.0,
                          ),
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          labelText: "비밀번호를 입력하세요.",
                          contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                          ),
                        ),
                  
                      decoration: BoxDecoration(
                        //  color: Colors.grey[300],
                        border: Border.all(
                          width: 1,
                          color: Colors.grey
                        )
                      ),
              ),
              SizedBox(
                height: 10,
              ),
              Container(
                        child: 
                        TextField(
                        textInputAction: TextInputAction.go,
                        textAlign: TextAlign.start,
                        style: TextStyle(
                          color: Colors.black, 
                          fontSize: 16.0,
                          ),
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          labelText: "비밀번호 확인을 입력하세요.",
                          contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                          ),
                        ),
                  
                      decoration: BoxDecoration(
                        //  color: Colors.grey[300],
                        border: Border.all(
                          width: 1,
                          color: Colors.grey
                        )
                      ),
              ),
              Text("비밀번호는 최대 6자리로 입력해주세요.", style: TextStyle(fontSize: 13, fontWeight: FontWeight.w600,),),
              SizedBox(
                height: 10,
              ),
              Row(children: [
                
                  Expanded(
                    child: Container(
                        child: 
                        TextField(
                        textInputAction: TextInputAction.go,
                        textAlign: TextAlign.start,
                        style: TextStyle(
                          color: Colors.black, 
                          fontSize: 16.0,
                          ),
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          labelText: "주소를 입력하세요.",
                          contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                          ),
                        ),
                  
                      decoration: BoxDecoration(
                        //  color: Colors.grey[300],
                        border: Border.all(
                          width: 1,
                          color: Colors.grey
                        )
                      ),
                    ),
                    flex: 1,
                  ),
                  ElevatedButton(
                    onPressed: () => {}, 
                    child: Text('주소검색'),
                    style: ElevatedButton.styleFrom(
                      fixedSize: Size(100, 50),
                      primary: Colors.black,
                        onPrimary: Colors.white,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.zero,
                            side: BorderSide(color: Colors.grey)
                      ),
                    ),
                    
                  ),
              ],),
              SizedBox(
                height: 10,
              ),
              Container(
                        child: 
                        TextField(
                        textInputAction: TextInputAction.go,
                        textAlign: TextAlign.start,
                        style: TextStyle(
                          color: Colors.black, 
                          fontSize: 16.0,
                          ),
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          labelText: "이메일을 입력하세요.",
                          contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                          ),
                        ),
                  
                      decoration: BoxDecoration(
                        //  color: Colors.grey[300],
                        border: Border.all(
                          width: 1,
                          color: Colors.grey
                        )
                      ),
              ),
              Expanded(
                  child: 
                  Row(children: [
                  Expanded(
                    child:  ElevatedButton(
                          onPressed: () => {}, 
                          child: Text('다음'),
                          style: 
                          ElevatedButton.styleFrom(
                            fixedSize: Size.fromHeight(50),
                            primary: Colors.black,
                            onPrimary: Colors.white
                          ),
                        ),
                    flex: 1,
                  ), 
                  ],),
                  flex: 1,
                )
            ],
          ),
          ),
        ),
    );
  }
}