package commons

import parametric.typeDefinition.{ArrayType, Helper, RecordType, unionType}

trait arrayTypes extends fieldTypes with basicTypes with recordTypes{

  val arr_null = ArrayType(null1,1)
  val arr_bool = ArrayType(bool1,1)
  val arr_num = ArrayType(num1,1)
  val arr_str = ArrayType(str1,1)

  val arr_rec_abc = ArrayType(in_abc,1)
  val arr_rec_abd = ArrayType(in_abd,1)

  val h = new Helper()
  val order = h.whichOrdering("k")

  val arr_null_bool = ArrayType(unionType(List(null1,bool1).sortWith(order(_,_)<0)),2)

}
