package commons

import parametric.typeDefinition.{RecordType, unionType}

trait recordTypes extends fieldTypes {
  val in_abc = RecordType(List(a1,b1,c1).sorted,1)
  val in_abc2 = RecordType(List(a1,b1,c1).sorted,2)

  val in_abd = RecordType(List(a1,b1,d1).sorted,1)
  val in_def = RecordType(List(d1,e1,f1).sorted,1)

  val res_abc2 = RecordType(List(a2,b2,c2).sorted,2)
  val res_abcd = RecordType(List(a2,b2,c1,d1).sorted,2)
  val res_abcdef = RecordType(List(a1,b1,c1,d1,e1,f1).sorted,2)



}
