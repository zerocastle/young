class Board {
  late int bCd;

  late String bTitle;
  late String bCont;
  late String bDate;
  late num bLike;
  late num bVisit;
  late String mId;

  Board({
    num? bCd = 0,
    String? bTitle = "",
    String? bCont = "",
    String? bDate = "",
    num? bLike = 0,
    num? bVisit = 0,
    String? mId = "",
  });

  factory Board.fromJson(Map<String, dynamic> json) {
    return Board(
        bCd: json['bCd'],
        bTitle: json['bTitle'],
        bCont: json['bCont'],
        bDate: json['bDate'],
        bLike: json['bLike'],
        bVisit: json['bVisit'],
        mId: json['mId']);
  }
}

// class BoardVOList{
//   final List<Board> boards;

//   BoardVOList({required this.boards});

//   factory BoardVOList.fromJson(List<dynamic> parsedJson) {
//     List<Board> boards = <Board>[];

//     return new BoardVOList(
//        boards: boards,
//     );
//   }
// }
