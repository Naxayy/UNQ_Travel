package ar.edu.unq.travel.notificaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VoucherTest {

    @Test
    public void testGetMontoDevuelveElMontoAsignado() {
        Voucher voucher = new Voucher(4250.50);

        assertEquals(4250.50, voucher.getMonto());
    }
}