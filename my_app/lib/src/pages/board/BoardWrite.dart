import 'dart:ffi';
import 'dart:io';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class BoardWrite extends StatefulWidget {
  const BoardWrite({ Key? key }) : super(key: key);

  @override
  _BoardWriteState createState() => _BoardWriteState();
}

class _BoardWriteState extends State<BoardWrite> {
  String imagePath = "";
  List<XFile> images = [];  
  
  final picker = ImagePicker();
  @override
  Widget build(BuildContext context) {
    
    print("images111 ::::: $images" );
    return Scaffold(
      body:SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(0.0),
          child: CustomScrollView(
            slivers: <Widget>[
                SliverAppBar(
                 title: Text('업체 작성'),
                 floating: true,
                 expandedHeight: 50,
                 centerTitle: true,
                ),
                SliverToBoxAdapter(
                  child: Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Text("업체명", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                        SizedBox(height: 10,),
                        Container(
                          child: TextField(
                          textInputAction: TextInputAction.go,
                          style: TextStyle(
                            color: Colors.black, 
                            fontSize: 16.0,
                            ),
                          decoration: InputDecoration(
                            border: InputBorder.none,
                            labelText: "업체명을 입력하세요.",
                            contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                            ),
                          ),
                         decoration: BoxDecoration(
                           color: Colors.grey[300],
                         ),
                         
                        ),
                        SizedBox(height: 10,),
                        Text("업체 대표사진", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                        OutlinedButton(
                          onPressed: () async {
                            final pickedFile = await picker.pickImage(source: ImageSource.gallery);
                            if (pickedFile != null) {
                              setState(() {
                                imagePath = pickedFile.path;
                              });
                            }
                          },
                          child:   imagePath != ""
                          ? Container(
                            padding: EdgeInsets.symmetric(horizontal: 15),
                            child: Image.file(File(imagePath))
                          ):        
                          Container(
                            height: 200,
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Icon(
                                  Icons.add_photo_alternate_outlined,
                                ),
                                Text("사진등록")
                              ],
                            ),
                            color: Colors.grey[300],
                          ),
                          style: OutlinedButton.styleFrom(
                            primary: Colors.grey,
                            side: BorderSide(color: Colors.white, width: 0),
                          ),
                        ),
                        SizedBox(height: 10,),

                        Text("업체 사진", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                          OutlinedButton(
                          onPressed: () async {
                            final pickedFileList = await picker.pickMultiImage();
                            if (pickedFileList != null) {
                               print("pickedFileList ::::: $pickedFileList" );
                              setState(() {
                                images = pickedFileList;
                                print("images ::::: $images" );
                              });
                            }
                          },
                          child:   images.isEmpty
                          ? Row(
                            // mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: [
                              Container(
                                width: 90,
                                height: 90,
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  children: [
                                    Icon(
                                      Icons.add_outlined,
                                    ),
                                    // Text("사진등록")
                                  ],
                                ),
                                color: Colors.grey[300],
                              ),
                              SizedBox(width: 20,),
                              // Container(
                              //   width: 90,
                              //   height: 90,
                              //   child: Row(
                              //     mainAxisAlignment: MainAxisAlignment.center,
                              //     children: [
                              //     ],
                              //   ),
                              //   color: Colors.grey[300],
                              // )
                            ],
                            )
                          :        
                          Container(
                            padding: EdgeInsets.symmetric(horizontal: 15),
                            child: ListView.builder(
                              key: UniqueKey(),
                              itemBuilder: (context, index) {
                                // Why network for web?
                                // See https://pub.dev/packages/image_picker#getting-ready-for-the-web-platform
                                return Semantics(
                                  label: 'image_picker_example_picked_image',
                                  child: Image.file(File(images[index].path)),
                                );
                              },
                              itemCount: images.length,
                              shrinkWrap: true,
                            )
                          ),
                          style: OutlinedButton.styleFrom(
                            primary: Colors.grey,
                            side: BorderSide(color: Colors.white, width: 0),
                          ),
                        ),
                        SizedBox(height: 10,),
                        
                        Text("전화번호", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                        SizedBox(height: 10,),
                        Container(
                          child: TextField(
                          textInputAction: TextInputAction.go,
                          style: TextStyle(
                            color: Colors.black, 
                            fontSize: 16.0,
                            ),
                          decoration: InputDecoration(
                            border: InputBorder.none,
                            labelText: "예)01012345678(-빼고 입력해주세요.)",
                            contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                            ),
                          ),
                         decoration: BoxDecoration(
                           color: Colors.grey[300],
                         ),
                         
                        ),
                        SizedBox(height: 10,),
                        Text("업체소개글", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                        SizedBox(height: 10,),
                        Container(
                          child: TextField(
                          textInputAction: TextInputAction.go,
                          maxLines: 10,
                          textAlign: TextAlign.start,
                          style: TextStyle(
                            color: Colors.black, 
                            fontSize: 16.0,
                            ),
                          decoration: InputDecoration(
                            border: InputBorder.none,
                            labelText: "업체 설명을 입력해주세요.",
                            contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                            ),
                          ),
                         decoration: BoxDecoration(
                           color: Colors.grey[300],
                         ),
                         
                        ),
                        SizedBox(height: 10,),
                        Text("업체유형", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                        SizedBox(height: 10,),
                        Row(
                          children: [
                            ElevatedButton(
                            onPressed: () => {}, 
                            child: Text('숙소'),
                            ),
                            SizedBox(width: 15,),
                            ElevatedButton(
                            onPressed: () => {}, 
                            child: Text('외식'),
                            ),
                            SizedBox(width: 15,),
                            ElevatedButton(
                            onPressed: () => {}, 
                            child: Text('병원'),
                            ),
                            SizedBox(width: 15,),
                            ElevatedButton(
                            onPressed: () => {}, 
                            child: Text('서비스'),
                            ),
                          ]
                          ,),
                          Divider(
                            color: Colors.grey[700],
                          ),
                           Row(
                          children: [
                            ElevatedButton(
                            onPressed: () => {}, 
                            child: Text('펜션'),
                            ),
                            SizedBox(width: 15,),
                            ElevatedButton(
                            onPressed: () => {}, 
                            child: Text('호텔'),
                            ),
                            SizedBox(width: 15,),
                            ElevatedButton(
                            onPressed: () => {}, 
                            child: Text('모텔'),
                            ),
                            SizedBox(width: 15,),
                            ElevatedButton(
                            onPressed: () => {}, 
                            child: Text('게스트하우스'),
                            ),
                          ]
                          ,),
                          SizedBox(height: 20,),
                          Text("영업시간", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                          SizedBox(height: 20,),
                          Container(
                            child: TextField(
                            textInputAction: TextInputAction.go,
                            style: TextStyle(
                              color: Colors.black, 
                              fontSize: 16.0,
                              ),
                            decoration: InputDecoration(
                              border: InputBorder.none,
                              labelText: "00:00~00:00",
                              contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                              ),
                            ),
                          decoration: BoxDecoration(
                            color: Colors.grey[300],
                          ),
                          
                          ),
                          SizedBox(height: 20,),
                          Text("할인 내용", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                          SizedBox(height: 20,),
                          Container(
                            child: TextField(
                            textInputAction: TextInputAction.go,
                            style: TextStyle(
                              color: Colors.black, 
                              fontSize: 16.0,
                              ),
                            decoration: InputDecoration(
                              border: InputBorder.none,
                              labelText: "예) 30%, 환급 1000원 할인, 음료 1개 공짜, 할인표 참고",
                              contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                              ),
                            ),
                          decoration: BoxDecoration(
                            color: Colors.grey[300],
                          ),
                          
                          ),
                          SizedBox(height: 20,),
                          Text("할인 대상", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                          SizedBox(height: 20,),
                          Container(
                            width: 60,
                            height: 40,
                            alignment: Alignment.center,
                            child: Text('병장', textAlign: TextAlign.center, style: TextStyle(fontSize: 20),),
                            decoration: BoxDecoration(
                              color: Colors.grey[300],
                              borderRadius:  new BorderRadius.all(
                                Radius.circular(10),
                              )
                            ),
                          ),
                          SizedBox(height: 20,),
                          Row(children: [
                          Text("홈페이지 등록", style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700),),
                          Icon(
                            Icons.link_outlined,
                          ),
                          ],),
                          SizedBox(height: 20,),
                          Container(
                            child: TextField(
                            textInputAction: TextInputAction.go,
                            style: TextStyle(
                              color: Colors.black, 
                              fontSize: 16.0,
                              ),
                            decoration: InputDecoration(
                              border: InputBorder.none,
                              labelText: "www.naver.com",
                              contentPadding: EdgeInsets.symmetric(horizontal: 20.0)
                              ),
                            ),
                          decoration: BoxDecoration(
                            color: Colors.grey[300],
                          ),
                          
                          ),

                          ElevatedButton(
                          onPressed: () => {}, 
                          child: Text('등록하기', ),
                          style: ButtonStyle(
                            
                          ),
                          ),
                        
                      ],
                    ),
                  ),
                )
              ],
            ),
          ),
        )
    );
  }
}