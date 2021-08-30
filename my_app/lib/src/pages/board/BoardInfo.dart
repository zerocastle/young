import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:get/get.dart';
import 'package:my_app/common/common_widget.dart';
import 'package:my_app/data/network.dart';
import 'package:my_app/model/CommunityVO.dart';
import 'package:my_app/model/repleVo.dart';
import 'package:my_app/src/controller/boarderinfo_controller.dart';

class BoardInfo extends StatefulWidget {
  final User? user;
  final CommunityVO? param;

  // final dynamic param;
  const BoardInfo({Key? key, this.param, this.user}) : super(key: key);

  @override
  _BoardInfoState createState() => _BoardInfoState();
}

class _BoardInfoState extends State<BoardInfo> {
  late Future<dynamic> _boarderInfo;
  late Future<dynamic> _repleInfo;

  late dynamic _boarderInfoData;

  late String state = "";

  bool countSignal = true;

  CommonWidget _common = CommonWidget();

  // late BoarderinfoController _controller;

  @override
  // void initState() {
  //   // TODO: implement initState
  //   super.initState();
  //   init();
  //   print(_repleInfo.toString());
  // }

  Future init() async {
    // _boarderInfo = boardInfoOrder('/board/boardInfo', widget.param!);
    // _repleInfo = boardInfoOrder('/board/repleInfo', widget.param!);
    // _controller =  Get.put(BoarderinfoController());
  }

  @override
  Widget build(BuildContext context) {
    // Get.find<BoarderinfoController>();
    Get.put(BoarderinfoController());
    return Scaffold(
      body: SafeArea(
        child: GestureDetector(
          onTap: () {
            FocusScope.of(context).unfocus();
          },
          child: Padding(
              padding: const EdgeInsets.all(0.0),
              child: Obx(
                () => CustomScrollView(
                  slivers: <Widget>[
                    SliverAppBar(
                      title: Text('글 작성 페이지'),
                      floating: true,
                      // flexibleSpace: Placeholder(),
                      expandedHeight: 50,
                    ),
                    SliverToBoxAdapter(
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Column(
                          children: <Widget>[
                            _getInfoComp(),
                            _repleForm(),
                            _count(),
                          ],
                        ),
                      ),
                    ),
                    SliverPadding(
                        padding: const EdgeInsets.all(8.0),
                        sliver: _getRepleComp())
                  ],
                ),
              )),
        ),
      ),
    );
  }

  /* #=========================================# 
          | 아래 부터 함수 단위 적용| 
    #=========================================#
  */

  /* *************************************** start 글 상세 페이지 ***************************************** */
// 글 상세 페이지 컴포넌트 (단건);
  Widget _getInfoComp() {
    final BorderRadius _baseBorderRadius = BorderRadius.circular(8);

    CircleAvatar image =
        CircleAvatar(backgroundImage: AssetImage('assets/fire.png'));
    const _padding = EdgeInsets.fromLTRB(15.0, 2.0, 10.0, 0.0);

    CommunityVO vo = BoarderinfoController.to.boardInfo.value;

    if (vo.mid == null) {
      return Center(
          child: Padding(
        padding: const EdgeInsets.only(top: 15),
        child: CircularProgressIndicator(),
      ));
    }

    String? btitle = vo.btitle;
    String? bcont = vo.bcont;
    String? classnm = vo.classnm;
    int? blike = vo.blike;
    int? bvisit = vo.bvisit;
    String? bdate = vo.bdate;
    String? mid = vo.mid;

    return Container(
      margin: EdgeInsets.only(top: 7.0),
      padding: EdgeInsets.all(5.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            width: 15,
            height: 25,
            color: Colors.black,
          ),
          Container(
            padding: _padding,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(btitle!,
                    style:
                        TextStyle(fontWeight: FontWeight.w700, fontSize: 24)),
                InkWell(
                  child: Container(
                    child: Chip(
                      label: Icon(
                        Icons.share,
                        size: 24,
                      ),
                    ),
                  ),
                  onTap: () {
                    print('공유');
                  },
                )
              ],
            ),
          ),
          SizedBox(
            height: 10.0,
          ),
          Container(
            padding: _padding,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                image,
                SizedBox(
                  width: 10.0,
                ),
                Container(
                    child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                      Text(
                        '$mid 님 |  $classnm',
                        style: TextStyle(
                            fontSize: 12.0, fontWeight: FontWeight.w700),
                      ),
                      SizedBox(
                        height: 5.0,
                      ),
                      Text(bdate!)
                    ]))
              ],
            ),
          ),
          SizedBox(
            height: 15.0,
          ),
          Container(
            padding: _padding,
            child: RichText(
              // overflow: TextOverflow.ellipsis,
              strutStyle: StrutStyle(fontSize: 12.0),
              text:
                  TextSpan(style: TextStyle(color: Colors.black), text: bcont),
            ),
          ),
          SizedBox(
            height: 30.0,
          ),
          _getIcon(vo),
        ],
      ),
      decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.all(Radius.circular(10)),
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.5),
              spreadRadius: 3,
              blurRadius: 4,
              offset: Offset(0, 3),
            )
          ]),
    );
  }

/* *************************************** end 글 상세 페이지 ***************************************** */

/* *************************************** start 댓글 리스트 ***************************************** */
  Widget _getRepleComp() {
    // 이미지 (파일 이미지 나중에 추가)
    CircleAvatar image =
        CircleAvatar(backgroundImage: AssetImage('assets/fire.png'));

    return SliverList(
      delegate: SliverChildBuilderDelegate(
        (context, index) {
          
          RepleVo vo = BoarderinfoController.to.repleVoList.value.items![index];

          return Container(
            margin: EdgeInsets.only(top: 7.0),
            padding: const EdgeInsets.all(8.0),
            child: Column(children: <Widget>[
              ListTile(
                leading: image,
                onTap: (){},
                title: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text('hellow')
                  ],)
                ) ,
            ]),
            decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.all(Radius.circular(10)),
                boxShadow: [
                  BoxShadow(
                    color: Colors.grey.withOpacity(0.5),
                    spreadRadius: 3,
                    blurRadius: 4,
                    offset: Offset(0, 3),
                  )
                ]),
          );
        },
        childCount: BoarderinfoController.to.repleVoList.value.items!.length,
      ),
    );
  }

/* *************************************** end 댓글 리스트 ***************************************** */

// 아이콘들 컨테이터
  Widget _getIcon(CommunityVO data) {
    // 방문자 수
    int? visite = data.bvisit;
    // 좋아요 수
    int? blike = data.blike;
    const _padding = EdgeInsets.fromLTRB(15.0, 2.0, 10.0, 0.0);

    return Container(
      padding: _padding,
      child: Row(
        children: [
          Icon(
            Icons.remove_red_eye_outlined,
            size: 12.0,
          ),
          SizedBox(
            width: 5,
          ),
          Text(visite.toString()),
          SizedBox(
            width: 5,
          ),
          Icon(
            Icons.favorite,
            color: Colors.pink,
            size: 12.0,
          ),
          SizedBox(
            width: 5,
          ),
          Text(blike.toString()),
        ],
      ),
    );
  }

/* *************************************** start 댓글 컴포넌트 ***************************************** */

  Widget _repleForm() {
    OutlineInputBorder _border =
        OutlineInputBorder(borderRadius: BorderRadius.circular(8));

    // 텍스트 필드와 연결 해야한다.
    TextEditingController _commentController = TextEditingController();

    void _handleSubmitted(String text) {
      _commentController.clear();
      _writeComment(text);
    }

    GlobalKey<FormState> _formKey = GlobalKey<FormState>();

    return Container(
      padding: const EdgeInsets.all(8.0),
      margin: EdgeInsets.only(top: 10.0),
      child: Row(
        children: <Widget>[
          Flexible(
            child: TextField(
              // focusNode: FocusNode(),
              cursorColor: Colors.black,
              controller: _commentController,
              onSubmitted: (text) {
                _writeComment(text);
                _commentController.text = '';
              },
              decoration: InputDecoration(
                  labelText: '댓글을 입력 해 주세요',
                  border: _border,
                  filled: true,
                  enabledBorder: _border,
                  focusedBorder: _border),
              keyboardType: TextInputType.text,
              // obscureText: true,
            ),
          ),
          Container(
              child: IconButton(
                  onPressed: () {
                    _handleSubmitted(_commentController.text);
                  },
                  icon: Icon(Icons.send)))
        ],
      ),
      decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.all(Radius.circular(10)),
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.5),
              spreadRadius: 3,
              blurRadius: 4,
              offset: Offset(0, 3),
            )
          ]),
    );
  }

// 댓글작성
  Future<void> _writeComment(String text) async {
    
  }

  // 댓글수 표현
  Widget _count() {
    return Container(
      margin: EdgeInsets.only(top: 7.0),
      padding: EdgeInsets.all(5.0),
      child: Row(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Container(
                color: Colors.blue[50],
                child: Column(
                  children: <Widget>[
                    Text(
                        '댓글 수 : ${BoarderinfoController.to.repleVoList.value.items!.length}')
                  ],
                ))
          ]),
      decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.all(Radius.circular(10)),
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.5),
              spreadRadius: 3,
              blurRadius: 4,
              offset: Offset(0, 3),
            )
          ]),
    );
  }

/* *************************************** end 댓글 컴포넌트 ***************************************** */

}
