package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.api.BankParser;
import com.danwalkerdev.statement.api.ParserProvider;

public class NatwestParserProvider implements ParserProvider {
    @Override
    public BankParser getBankParser() {
        return new NatwestParser();
    }
}
