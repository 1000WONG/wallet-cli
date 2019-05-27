package org.tron.core.zen.address;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.tron.common.utils.ByteUtil;
import org.tron.common.zksnark.Librustzcash;

// ivk
@Slf4j(topic = "shieldTransaction")
@AllArgsConstructor
public class IncomingViewingKey {

  @Setter
  @Getter
  public byte[] value; // 256

  public Optional<PaymentAddress> address(DiversifierT d) {
    byte[] pkD = new byte[32]; // 32
    if (Librustzcash.librustzcashCheckDiversifier(d.data)) {
      Librustzcash.librustzcashIvkToPkd(value, d.data, pkD);
      log.debug("address.ivk is: " + ByteUtil.toHexString(value));
      log.debug("address.d is: " + ByteUtil.toHexString(d.data));
      log.debug("address.pkd is: " + ByteUtil.toHexString(pkD));
      return Optional.of(new PaymentAddress(d, pkD));
    } else {
      return Optional.empty();
    }
  }
}
