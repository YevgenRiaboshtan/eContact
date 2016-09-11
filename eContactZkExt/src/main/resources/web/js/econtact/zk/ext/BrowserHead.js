econtact.zk.ext.BrowserHead = zk.$extends(zul.sel.Listhead, {

    getZclass: function () {
        return this._zclass != null ? this._zclass: "z-listhead";
    }
});
