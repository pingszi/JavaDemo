module cn.pings.modulea {
    exports cn.pings.module1;
    provides cn.pings.module1.Test2 with cn.pings.module1.Test2.Test2Impl;
}