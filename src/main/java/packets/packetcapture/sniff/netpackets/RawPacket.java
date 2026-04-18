package packets.packetcapture.sniff.netpackets;

import java.time.Instant;
import java.util.Arrays;

/**
 * Raw packet constructor for retrieving packets of the wire.
 */
public class RawPacket {

    private final Instant instant;
    private final int payloadSize;
    private final byte[] payload;

    public static RawPacket newPacket(byte[] rawData, Instant ts) {
        return new RawPacket(rawData, ts);
    }

    public RawPacket(byte[] data, Instant ins) {
        instant = ins;
        payloadSize = data.length;
        payload = data;
    }

    public Instant getInstant() {
        return instant;
    }

    public int getPayloadSize() {
        return payloadSize;
    }

    public byte[] getPayload() {
        return payload;
    }

    public EthernetPacket getNewEthernetPacket() {
        return new EthernetPacket(payload, this);
    }

    /**
     * Attempts to parse the raw bytes directly as an IPv4 packet, bypassing
     * Ethernet frame parsing. Handles two link-layer formats used by TUN
     * interfaces (e.g. WireGuard / ProtonVPN on Windows):
     *
     *   - NULL/loopback (DLT_NULL): 4-byte address-family header followed by IP.
     *     AF_INET = 2, stored little-endian (02 00 00 00) on Windows.
     *   - Raw IP (DLT_RAW): IPv4 header starts at byte 0.
     *
     * Returns null if the data doesn't look like IPv4.
     */
    public Ip4Packet getDirectIp4Packet() {
        if (payload == null || payload.length < 20) return null;

        // NULL/loopback link type: 4-byte AF header, AF_INET=2
        if (payload.length >= 24) {
            boolean leAFInet = payload[0] == 2 && payload[1] == 0 && payload[2] == 0 && payload[3] == 0;
            boolean beAFInet = payload[0] == 0 && payload[1] == 0 && payload[2] == 0 && payload[3] == 2;
            if ((leAFInet || beAFInet) && ((payload[4] & 0xF0) >> 4) == 4) {
                return new Ip4Packet(Arrays.copyOfRange(payload, 4, payload.length), null);
            }
        }

        // Raw IPv4: version nibble in the first byte == 4
        if (((payload[0] & 0xF0) >> 4) == 4) {
            return new Ip4Packet(payload, null);
        }

        return null;
    }

    @Override
    public String toString() {
        return "RawPacket{" +
                "\n instant=" + instant +
                "\n payloadSize=" + payloadSize +
                "\n payload=" + Arrays.toString(payload);
    }
}
