econtact.zk.ext.BrowserHeader = zk.$extends(zul.sel.Listheader, {

    getZclass: function () {
        return this._zclass != null ? this._zclass: "z-listheader";
    }
});
