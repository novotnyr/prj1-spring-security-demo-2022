package sk.upjs.sk.springsecuritydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BankController {
    public static Logger logger = LoggerFactory.getLogger(BankController.class);

    @GetMapping("/accounts")
    @Secured({"ROLE_GUEST", "ROLE_ADMIN"})
    public BigDecimal getAccount(@AuthenticationPrincipal User user) {
        logger.info("Retrieving status for user {} with roles {}", user.getUsername(),
                user.getAuthorities());

        return BigDecimal.ONE;
    }

    @PostMapping("/accounts/withdrawals")
    @Secured("ROLE_WITHDRAWER")
    public void withdraw(@RequestBody MonetaryAmount amount) {
        logger.info("Withdrawing {}", amount);
    }
}
