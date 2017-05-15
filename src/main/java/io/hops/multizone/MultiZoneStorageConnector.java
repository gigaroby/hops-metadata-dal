package io.hops.multizone;

import io.hops.StorageConnector;
import io.hops.exception.StorageException;
import io.hops.transaction.TransactionCluster;
import org.apache.commons.logging.LogFactory;

/**
 * This class allows its clients to retrieve a connector for the required cluster (primary or local).
 */
public interface MultiZoneStorageConnector {
  /**
   * This method returns a StorageConnector for the appropriate cluster.
   * Note that on the primary cluster this parameter will be ignored as the primary cluster is also the local one.
   * @param cluster whether to connect to the local or primary cluster
   * @return the appropriate storage connector
   * @throws StorageException if a connector cannot be returned
   */
  StorageConnector connectorFor(TransactionCluster cluster) throws StorageException;
}
