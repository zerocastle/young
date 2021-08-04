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
                          ? Container(
                            height: 200,
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Text("사진등록")
                              ],
                            ),
                            color: Colors.grey[300],
                          ):        
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