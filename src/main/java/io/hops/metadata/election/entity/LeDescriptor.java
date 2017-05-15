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
package io.hops.metadata.election.entity;

import io.hops.metadata.common.FinderType;

public abstract class LeDescriptor
    implements Comparable<LeDescriptor>, Cloneable {

  public interface LeDescriptorFinder<T extends LeDescriptor>
      extends FinderType {
  }

  public static final int DEFAULT_PARTITION_VALUE = 0;
  private long id;
  private long counter;
  private String hostName;
  private String httpAddress;
  private final int partitionVal = 0;
  private String zone;
  private boolean connectedToPrimary;

  protected LeDescriptor() {
  }

  protected LeDescriptor(long id, long counter, String hostName,
      String httpAddress, String zone, boolean connectedToPrimary) {
    this.id = id;
    this.counter = counter;
    this.hostName = hostName;
    this.httpAddress = httpAddress;
    this.connectedToPrimary = connectedToPrimary;
    this.zone = zone;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCounter() {
    return counter;
  }

  public void setCounter(long counter) {
    this.counter = counter;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public String getHttpAddress() {
    return httpAddress;
  }

  public void setHttpAddress(String httpAddress) {
    this.httpAddress = httpAddress;
  }

  public int getPartitionVal() {
    return partitionVal;
  }

  public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
  }

  public boolean isConnectedToPrimary() {
    return connectedToPrimary;
  }

  public void setConnectedToPrimary(boolean connectedToPrimary) {
    this.connectedToPrimary = connectedToPrimary;
  }

  @Override
  public int compareTo(LeDescriptor l) {

    if (this.id < l.getId()) {
      return -1;
    } else if (this.id == l.getId()) {
      return 0;
    } else if (this.id > l.getId()) {
      return 1;
    } else {
      throw new IllegalStateException(
          "Leader.java: compareTo(...) is confused.");
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof LeDescriptor) {
      LeDescriptor l = (LeDescriptor) obj;
      //both are equal if all the fields match
      if (this.id == l.getId() && this.counter == l.getCounter() &&
          this.hostName.equals(l.getHostName())) {
        return true;
      } else {
        return false;
      }
    } else {
      throw new ClassCastException(
          "Leader.java: equals(...) can not compare the objects");
    }
  }

  @Override
  public int hashCode() {
    int hash = 1;
    hash = hash * 31 + this.hostName.hashCode();
    hash = hash * 31 + (new Long(id)).hashCode();
    hash = hash * 31 + (new Long(counter)).hashCode();
    return hash;
  }

  @Override
  public String toString() {
    return this.id + ", " + hostName + ", " + counter;
  }

  public static class YarnLeDescriptor extends LeDescriptor {

    public enum Finder implements LeDescriptorFinder<YarnLeDescriptor> {

      ById,
      All;

      @Override
      public Class getType() {
        return YarnLeDescriptor.class;
      }

      @Override
      public FinderType.Annotation getAnnotated() {
        switch (this) {
          case ById:
            return FinderType.Annotation.PrimaryKey;
          case All:
            return FinderType.Annotation.FullTable;
          default:
            throw new IllegalStateException();
        }
      }
    }

    public YarnLeDescriptor(long id, long counter, String hostName,
        String httpAddress) {
      super(id, counter, hostName, httpAddress, "primary", false);
    }
  }

  public static class HdfsLeDescriptor extends LeDescriptor {

    public enum Finder implements LeDescriptorFinder<HdfsLeDescriptor> {

      ById,
      All;

      @Override
      public Class getType() {
        return HdfsLeDescriptor.class;
      }

      @Override
      public FinderType.Annotation getAnnotated() {
        switch (this) {
          case ById:
            return FinderType.Annotation.PrimaryKey;
          case All:
            return FinderType.Annotation.FullTable;
          default:
            throw new IllegalStateException();
        }
      }
    }

    public HdfsLeDescriptor(long id, long counter, String hostName,
        String httpAddress, String zone, boolean connectedToPrimary) {
      super(id, counter, hostName, httpAddress, zone, connectedToPrimary);
    }
  }
}
