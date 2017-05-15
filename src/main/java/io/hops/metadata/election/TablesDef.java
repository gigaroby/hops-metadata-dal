/*
 * Copyright (C) 2015 hops.io.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.hops.metadata.election;

public class TablesDef {
  public interface LeDescriptorTableDef {
    String ID = "id";
    String COUNTER = "counter";
    String HOSTNAME = "hostname";
    String HTTP_ADDRESS = "httpAddress";
    String PARTITION_VAL = "partition_val";
    String ZONE = "zone";
    String CONNECTED_TO_PRIMARY = "connected_to_primary";
  }

  public interface HdfsLeaderTableDef extends LeDescriptorTableDef {
    String TABLE_NAME = "hdfs_le_descriptors";
  }

  public interface YarnLeaderTableDef extends LeDescriptorTableDef {
    String TABLE_NAME = "yarn_le_descriptors";
  }
}
