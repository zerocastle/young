import 'package:flutter/material.dart';

class DeptChoice extends StatefulWidget {
  const DeptChoice({ Key? key }) : super(key: key);

  @override
  _DeptChoiceState createState() => _DeptChoiceState();
}

class _DeptChoiceState extends State<DeptChoice> {
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
              Text("부대선택", style: TextStyle(fontSize: 30, fontWeight: FontWeight.w700),),
              SizedBox(height: 40,),
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
                          labelText: "부대를 입력하세요.",
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
                    child: Text('부대검색'),
                    style: ElevatedButton.styleFrom(
                      fixedSize: Size(100, 50),
                      primary: Colors.black
                    ),
                  ),
              ],),
              
                Expanded(
                  child: 
                  Row(children: [
                  Expanded(
                    child: Align(
                      alignment: FractionalOffset.bottomCenter,
                      child:
                      ElevatedButton(
                        onPressed: () => {}, 
                        child: Text('완료'),
                        style: 
                        ElevatedButton.styleFrom(
                          primary: Colors.black,
                            fixedSize: Size(350, 50),
                        ),
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