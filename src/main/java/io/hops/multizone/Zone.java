package io.hops.multizone;

/**
 * Represents a zone for the cluster.
 */
public enum Zone {
  /**
   * Namenode is in the same zone as the primary metadata storage cluster.
   */
  PRIMARY,
  /**
   * Namenode is in the same zone as the secondary metadata storage cluster.
   */
  SECONDARY;

  /**
   * Converts a string to a zone
   * @param s the string to convert
   * @return the proper value or null if no proper value is provided
   */
  public static Zone fromString(String s) {
    switch (s.toLowerCase()) {
      case "primary":
        return PRIMARY;
      case "secondary":
        return SECONDARY;
    }

    return null;
  }
}
