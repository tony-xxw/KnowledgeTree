package com.wynne.weekly.bytes;


import android.util.Log;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.weekly.R;

/**
 * Class文件格式: 无符号数,表
 * 无符号数: u1,u2,u4,u8 代表1,2,4,8个byte(字节) 用来描述数字,索引引用,数量值或按UTF-8编码构成字符串的值
 * 表: 由多个无符号数或其他表作为数据项构成的复合型数据类型,所有表都习惯性以"_info"结尾,用来表示复合型结构的数据
 *
 * 常量池
 * 字面量(Literal):文本字符串,final的常量值
 * 符号引用(Symbolic References)
 * -类和接口的全限定名
 * -字段的名称和描述符
 * -方法的名称和描述符
 *
 * 常量池分18种类型
 * u1 类型 2位 u2 类型 4位
 *
 * 常量池之后是访问标志
 * access_flags(访问标志): public,final,super,interface,abstract,synthetic,annotation,enum
 * 当存在以上访问标志后,则为真。
 * 例如: 存在public 和 super则access_flags访问标志为 0x0001 & 0x0020 = 0x0021
 *
 * 访问标志之后是类索引,父类索引,接口索引
 * 类索引 u2类型
 * 父类索引 u2类型
 * 接口索引 一组u2类型
 *
 * 索引值对应这常量池的数
 *
 *
 *
 */
public class ByteHandleActivity extends BaseActivity {


    @Override
    public void initView() {
        helloByte();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_weekly_byte_layout;
    }

    public void onClick(View v) {
    }

    public void helloByte() {
        int a = 1;
        int b = 2;
        int c = 3;
        reverse(a, b, c);
    }

    private void reverse(int a, int b, int c) {
        int temp = 0;
        temp = a;
        a = c;
        c = temp;

        for (int i = 0; i < a; i++) {
            Log.d("xxw", "i : " + i);
        }
    }

}
