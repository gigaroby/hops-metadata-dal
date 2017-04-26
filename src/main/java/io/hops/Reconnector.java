package io.hops;

import io.hops.exception.StorageException;

/**
 * A reconnector can report whether the connection is up and attempt reconnections.
 * Note that, if possible, checking for connectivity should be cheap
 * while reconnection is expected to be more expensive resource-wise.
 */
public interface Reconnector {
  /**
   * Checks whether the connector is connected to the remote.
   * @return whether the connection is up
   */
  boolean isConnected();

  /**
   * Attempts a reconnection.
   * If this method returns successfully, the connection attempt was a success.
   * Should be called periodically in the background to re-acquire connectivity
   */
  void reconnect() throws StorageException;
}
