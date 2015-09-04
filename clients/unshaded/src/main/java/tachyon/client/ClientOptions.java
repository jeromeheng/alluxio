/*
 * Licensed to the University of California, Berkeley under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package tachyon.client;

import tachyon.Constants;
import tachyon.conf.TachyonConf;
import tachyon.thrift.NetAddress;

/**
 * Represents the set of operation specific configuration options a user can pass into a Tachyon
 * client. Not all options will be valid for all operations.
 */
public class ClientOptions {

  /**
   * Builder for the <code>ClientOptions<</code>.
   */
  public static class Builder {
    private long mBlockSize;
    private CacheType mCacheType;
    private UnderStorageType mUnderStorageType;
    private NetAddress mLocation;

    /**
     * @param conf Tachyon configuration
     */
    public Builder(TachyonConf conf) {
      mBlockSize = conf.getBytes(Constants.USER_DEFAULT_BLOCK_SIZE_BYTE);
      mCacheType = conf.getEnum(Constants.USER_DEFAULT_CACHE_TYPE, CacheType.CACHE);
      mUnderStorageType =
          conf.getEnum(Constants.USER_DEFAULT_UNDER_STORAGE_TYPE, UnderStorageType.NO_PERSIST);
      mLocation = null;
    }

    /**
     * @param cacheType the cache type to use
     * @return the builder
     */
    public Builder setCacheType(CacheType cacheType) {
      mCacheType = cacheType;
      return this;
    }

    /**
     * @param location the location to use
     * @return the builder
     */
    public Builder setLocation(NetAddress location) {
      throw new UnsupportedOperationException("Set location is currently unsupported.");
    }

    /**
     * @param underStorageType the under storage type to use
     * @return the builder
     */
    public Builder setUnderStorageType(UnderStorageType underStorageType) {
      mUnderStorageType = underStorageType;
      return this;
    }

    /**
     * @param blockSize the block size to use
     * @return the builder
     */
    public Builder setBlockSize(long blockSize) {
      mBlockSize = blockSize;
      return this;
    }

    /**
     * Builds a new instance of <code>ClientOptions</code>
     *
     * @return a <code>ClientOptions</code> instance
     */
    public ClientOptions build() {
      return new ClientOptions(this);
    }
  }

  private final long mBlockSize;
  private final CacheType mCacheType;
  private final UnderStorageType mUnderStorageType;
  private final NetAddress mLocation;

  /**
   * @return the default <code>ClientOptions</code>
   */
  public static ClientOptions defaults() {
    return new Builder(new TachyonConf()).build();
  }

  private ClientOptions(ClientOptions.Builder builder) {
    mBlockSize = builder.mBlockSize;
    mCacheType = builder.mCacheType;
    mUnderStorageType = builder.mUnderStorageType;
    mLocation = builder.mLocation;
  }

  /**
   * @return the block size
   */
  public long getBlockSize() {
    return mBlockSize;
  }

  /**
   * @return the cache type
   */
  public CacheType getCacheType() {
    return mCacheType;
  }

  /**
   * @return the under storage type
   */
  public UnderStorageType getUnderStorageType() {
    return mUnderStorageType;
  }

  /**
   * @return the location
   */
  public NetAddress getLocation() {
    return mLocation;
  }
}
