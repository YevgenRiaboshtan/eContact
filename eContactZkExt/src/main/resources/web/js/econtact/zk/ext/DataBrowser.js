econtact.zk.ext.DataBrowser = zk.$extends(zul.sel.Listbox, {

    getZclass: function () {
        return this._zclass != null ? this._zclass: "z-listbox";
    }
});
