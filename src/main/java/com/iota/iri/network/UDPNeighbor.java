package com.iota.iri.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iota.iri.network.replicator.Replicator;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.zip.CRC32;

/**
 * Created by paul on 4/15/17.
 */
public class UDPNeighbor extends Neighbor {
    private static final Logger log = LoggerFactory.getLogger(Neighbor.class);

    public UDPNeighbor(InetSocketAddress address, boolean isConfigured) {
        super(address, isConfigured);
    }

    @Override
    public void send(DatagramPacket packet) {
        try {
            CRC32 crc32 = new CRC32();                                        
            crc32.update(packet.getData(),0,Node.TRANSACTION_PACKET_SIZE);
            String crc32_string = Long.toHexString(crc32.getValue());
            log.info("checksum sent="+crc32_string);
            while (crc32_string.length() < Replicator.CRC32_BYTES) crc32_string = "0" + crc32_string;
            byte [] crc32_bytes = crc32_string.getBytes();
            System.arraycopy(packet.getData(), Node.TRANSACTION_PACKET_SIZE, crc32_bytes, 0, Replicator.CRC32_BYTES);
            packet.setSocketAddress(getAddress());
            UDPReceiver.instance().send(packet);
            incSentTransactions();
        } catch (final Exception e) {
            log.error("UDP send error: {}",e.getMessage());
        }
    }

    @Override
    public int getPort() {
        return getAddress().getPort();
    }

    @Override
    public String connectionType() {
        return "udp";
    }
}
