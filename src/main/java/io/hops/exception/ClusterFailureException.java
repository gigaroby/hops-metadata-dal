package io.hops.exception;

public class ClusterFailureException extends StorageException {
  public ClusterFailureException(String message) {
    super(message);
  }

  public ClusterFailureException(Throwable cause) {
    super(cause);
  }

  public ClusterFailureException(String message, Throwable cause) {
    super(message, cause);
  }
}
