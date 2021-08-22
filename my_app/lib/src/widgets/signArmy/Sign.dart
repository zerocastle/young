import 'package:flutter/material.dart';

class Sign extends StatefulWidget {
  const Sign({ Key? key }) : super(key: key);

  @override
  _SignState createState() => _SignState();
}

class _SignState extends State<Sign> {
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
              Text("복무타입", style: TextStyle(fontSize: 30, fontWeight: FontWeight.w700),),
              SizedBox(height: 40,),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                    ElevatedButton(
                      onPressed: () => {},
                      child: Text('육군'),
                      style: ElevatedButton.styleFrom(
                        fixedSize: Size(150, 50),
                        elevation: 0.0,
                        primary: Colors.black,
                        onPrimary: Colors.white,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.zero,
                            side: BorderSide(color: Colors.grey)
                        ),
                        
                      ),
                    ),  
                  ElevatedButton(
                    onPressed: () => {},
                    child: Text('해군'),
                    style: ElevatedButton.styleFrom(

                        fixedSize: Size(150, 50),
                        elevation: 0.0,
                        primary: Colors.white,
                        onPrimary: Colors.black,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.zero,
                          side: BorderSide(color: Colors.grey)
                        ),
                    ),
                  ),
                ],
              ),
              SizedBox(height: 15,),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    onPressed: () => {},
                    child: Text('공군'),
                    style: ElevatedButton.styleFrom(
                      fixedSize: Size(150, 50),
                      elevation: 0.0,
                      primary: Colors.white,
                      onPrimary: Colors.black,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.zero,
                          side: BorderSide(color: Colors.grey)
                      ),

                    ),
                  ),
                  ElevatedButton(
                    onPressed: () => {},
                    child: Text('해병대'),
                    style: ElevatedButton.styleFrom(

                      fixedSize: Size(150, 50),
                      elevation: 0.0,
                      primary: Colors.white,
                      onPrimary: Colors.black,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.zero,
                          side: BorderSide(color: Colors.grey)
                      ),
                    ),
                  ),
                ],
              ),
                Expanded(
                  child: 
                  Row(children: [
                  Expanded(
                    child: Align(
                      alignment: FractionalOffset.bottomCenter,
                      child:
                      ElevatedButton(
                        onPressed: () => {}, 
                        child: Text('다음'),
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