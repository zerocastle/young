import 'package:flutter/material.dart';

class CommonWidget extends StatelessWidget {
  // double height;
  // double widht;

  // CommonWidget({this.height = 0.0, this.widht = 0.0});
  const CommonWidget({ Key? key }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container();
  }

  // 사이즈드 박스
  Widget getSizedBox(double width , double heigth) {
    return SizedBox(
      width: width,
      height: heigth,
    );
  }

  //  디바이더도 나중에 값 받아서 할지는 봅세
  Widget getDivider() {
    return Divider(
        height: 40.0, color: Colors.grey[850], thickness: 0.5, endIndent: 30.0);
  }
}
