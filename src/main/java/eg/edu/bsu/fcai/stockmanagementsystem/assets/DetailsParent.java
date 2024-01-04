package eg.edu.bsu.fcai.stockmanagementsystem.assets;

import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetPermissionsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldGetPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutPermissionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public abstract class DetailsParent {
    protected final MainPutDetailsService mainPutDetailsService;
    protected final MainPutPermissionService mainPutPermissionService;
    protected final MainGetPermissionService mainGetPermissionService;
    protected final MainGetDetailsService mainGetDetailsService;
    protected final OldGetPermissionService oldGetPermissionService;
    protected final OldGetDetailsService oldGetDetailsService;
    protected final OldPutPermissionService oldPutPermissionService;
    protected final OldPutDetailsService oldPutDetailsService;
    protected final ConsumedPutPermissionService consumedPutPermissionService;
    protected final ConsumedPutDetailsService consumedPutDetailsService;
    protected final ConsumedGetPermissionsService consumedGetPermissionsService;
    protected final ConsumedGetDetailsService consumedGetDetailsService;
}
