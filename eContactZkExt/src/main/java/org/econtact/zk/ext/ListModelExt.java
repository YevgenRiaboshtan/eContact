package org.econtact.zk.ext;

import org.econtact.data.criteria.SortingInfo;
import org.econtact.data.filter.Filter;
import org.zkoss.zul.ext.Selectable;

import java.util.List;

/**
 * This interface defines the methods that components the {@link DataBrowser} use to get the content of items.
 * Contains two type of filter:<ul>
 * <li>immutable - defines by developer and can not changed by user</li>
 * <li>user - defines by user to filter data from DB in runtime</li>
 * </ul>
 */
public interface ListModelExt<E> extends Selectable<E> {
    /**
     * Returns total size of content items.
     *
     * @return total size of items.
     */
    int getTotalSize();

    /**
     * Synchronizes model with DB.
     *
     * @param page           number of current page in {@link DataBrowser} component.
     * @param pageSize       size of page in {@link DataBrowser} component.
     * @param clearSelection clear all selections on page after synchronized model with DB.
     * @param force          synchronize model with DB immediate.
     */
    void syncModel(int page, int pageSize, boolean clearSelection, boolean force);

    /**
     * Sets filter defines by developer, in other words - system restrictions.
     *
     * @param filter filter of system restrictions.
     */
    void setImmutableFilter(Filter filter);

    /**
     * Sets the user filter.
     *
     * @param filter user filter.
     */
    void setUserFilter(Filter filter);

    /**
     * Sets the information about sorting data from DB.
     *
     * @param sortingInfos list {@link SortingInfo} about sorting data from DB.
     */
    void setSortingInfos(List<SortingInfo> sortingInfos);

    /**
     * Sets model as empty. Empty model returns total size as 0, and not sends any request to DB.
     *
     * @param empty TRUE - model is empty, FALSE - model gets data from DB.
     */
    void setEmptyModel(boolean empty);
}
