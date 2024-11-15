module jmp.app {
    uses com.epam.backend.core.service.Service;
    uses com.epam.backend.core.service.Bank;
    requires jmp.dto;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;

    exports com.epam.backend.core;
}