import 'package:flutter/material.dart';
import 'package:intl/locale.dart';
import 'package:scroll_date_picker/scroll_date_picker.dart';
import 'package:intl/intl.dart';
import 'package:intl/date_symbol_data_local.dart';
class DateChoice extends StatefulWidget {
  const DateChoice({ Key? key }) : super(key: key);

  @override
  _DateChoiceState createState() => _DateChoiceState();
}


class _DateChoiceState extends State<DateChoice> {
  late DatePickerController _controller;
  DateTime _selectedDate = DateTime.now();

   @override
  void initState() {
    super.initState();
    _controller = DatePickerController(
        initialDateTime: DateTime.now(), minYear: 1900, maxYear: 2050);

  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    initializeDateFormatting();
    return Scaffold(
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Column(
            // mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text("입대날짜", style: TextStyle(fontSize: 30, fontWeight: FontWeight.w700),),
              Container(
              height: 150.0,
              alignment: Alignment.center,
              child :Text(DateFormat('yyyy . MM . dd E요일', 'ko_KR').format(_selectedDate).toString(),
                style: TextStyle(fontSize: 30.0, fontWeight: FontWeight.w700),
                ),
              ),
              ScrollDatePicker(
                height: 130,
                controller: _controller,
                locale: DatePickerLocale.ko_kr,
                pickerDecoration: BoxDecoration(
                    border: Border.all(color: Colors.blueAccent, width: 2.0)),
                config: DatePickerConfig(
                    isLoop: true,
                    selectedTextStyle: TextStyle(
                        fontWeight: FontWeight.w600,
                        color: Colors.black,
                        fontSize: 17.0)),
                onChanged: (value) {
                  setState(() {
                    _selectedDate = value;
                  });
                },
              ),
              SizedBox(height: 60,),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text("입대일", style: TextStyle(fontSize: 17, fontWeight: FontWeight.w700),),
                  Text((DateFormat('yyyy . MM . dd (E)', 'ko_KR').format(_selectedDate).toString()),style: TextStyle(fontSize: 17, fontWeight: FontWeight.w500, color: Colors.grey),),
                ],
              ),
              SizedBox(height: 20,),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text("전역일", style: TextStyle(fontSize: 17, fontWeight: FontWeight.w700),),
                  Text((DateFormat('yyyy . MM . dd (E)', 'ko_KR').format(_selectedDate).toString()),style: TextStyle(fontSize: 17, fontWeight: FontWeight.w500, color: Colors.grey)),
                ],
              ),
            ],
          ),
          ),
        ),
    );
  }
}