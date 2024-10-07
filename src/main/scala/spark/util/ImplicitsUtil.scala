package spark.util

import org.apache.spark.sql.{Dataset, Encoder}

object ImplicitsUtil {

  implicit class DatasetOps[T](ds: Dataset[T]) {

    def collectMatch[U: Encoder](pf: PartialFunction[T, U]): Dataset[U] =
      ds.filter(data => pf.isDefinedAt(data)).map(data => pf(data))
  }
}
