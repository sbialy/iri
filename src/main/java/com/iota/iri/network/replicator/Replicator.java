package com.iota.iri.network.replicator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Replicator {
    
    public final static int CRC32_BYTES = 12;
    
    public static final int NUM_THREADS = 32;
    
    private static final Logger log = LoggerFactory.getLogger(Replicator.class);
    
    public void init(int port) {
        
        new Thread(ReplicatorSinkPool.instance()).start();
        new Thread(ReplicatorSourcePool.init(port)).start();
        log.info("Started ReplicatorSourcePool");
    }
    
    public void shutdown() {
        // TODO
    }
    
    private static final Replicator instance = new Replicator();

    private Replicator() {
    }

    public static Replicator instance() {
        return instance;
    }

}
