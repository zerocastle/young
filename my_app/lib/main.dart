import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';

import 'package:my_app/root_page.dart';
import 'package:my_app/src/binding/init_binding.dart';
import 'package:my_app/src/pages/shopping/Shopping.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var green = Colors.green;
    return GetMaterialApp(
      initialBinding: InitBinding(),
      initialRoute: "/",
      getPages: [
        // GetPage(name: "/shopping", page: ()=> Shopping() , binding: BindingsBuilder((){Get.put(ShoopingControoler())}))
      ],
      debugShowCheckedModeBanner: false,
      title: 'gunDabang',
      theme: ThemeData(primarySwatch: green),
      // home: MyHomePage(title: '군다방'),
      home: RootPage(),
    );
  }
}
