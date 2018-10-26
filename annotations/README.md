##安卓中的注解分为八大类：##

*Nullness注解：@Nullable、@NoNull（为方法的形参加上这个，可以减少判空的代码）
*资源类型注解：以Res结尾，例如@StringRes、@ColorRes、@IdRes等
*权限注解：@requestPermission，这个注解在PermissionDispatcher这个开源库中用到
*CallSuper注解：被@CallSuper修饰的方法，一定要调用super方法
*枚举注解：@IntDef和@StringDef，具体用法可以看Toast下Duration
*线程注解：@MainThread、@WorkThread等，被这些注解修饰的方法只能在该线程内调用
*变量限制注解：@Size、@IntRange、@FloatRange等
*结果检查注解：@CheckResult，被该注解修饰的方法，需要对方法的返回值进行处理。例如Context.checkPermission(@NonNull String permission, int pid, int uid)方法，防止别人误解该方法就已经算是请求权限了，调用该方法一定要判断返回值，权限是否被赋予，如果不判断，调用该方法无用。如果只是简单调该函数，并未判断返回值，则会提示是否使用另外一个函数。