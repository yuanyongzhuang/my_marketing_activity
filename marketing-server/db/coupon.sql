-- 券
# drop table voucher_info;
create table voucher_info
(
    id                   bigint unsigned primary key auto_increment not null comment 'id',
    activity_id          bigint         default 0                   not null comment '活动id',
    show_name            varchar(64)    default ''                  not null comment '展示名称（用户侧）',
    inner_code           varchar(32)    default ''                  not null comment '内部编码',
    stock                int            default 0                   not null comment '库存（可领取总量）',
    total_num            int            default 0                   not null comment '可领取数量（领取后要减数）',
    each_limit           int            default 0                   not null comment '领取次数[人] -1 不可领取, 0 不限制, 大于0 限制',

    use_range_type       tinyint        default 0                   not null comment '适用商品范围 0 全品类, 1 指定商品 2 指定品类',
    full_amounts         decimal(10, 2) default 0                   not null comment '满额（门槛价）',

    discount_type        tinyint        default 0                   not null comment '优惠方式 0 减免, 1 折扣, 2 一口价',
    discount_amounts     decimal(10, 2) default 0                   not null comment '减额(100)/折扣(0.8)',
    top_discount_amounts decimal(10, 2) default 0                   not null comment '减免封顶金额 0 上不封顶, 大于0 ',


    use_time_type        tinyint        default 0                   not null comment '可用时间分类 0 end, 1 x天可用, 2 start->end',
    use_time_start       datetime                                   null comment '可用起始时间',
    use_time_end         datetime                                   null comment '可用截止时间',
    use_time_plus_day    int            default 0                   not null comment '领取x天内可用',

    expand_json          json                                       null comment '扩展属性：指定品类/商品',

    operator             varchar(32)    default ''                  not null comment '操作人',
    enabled_status       tinyint        default 1                   not null comment '启用状态 1 是 0 否',
    delete_status        tinyint        default 0                   not null comment '删除状态 1 是 0 否',
    create_time          datetime       default now()               not null comment '创建时间',
    update_time          datetime       default now()               not null on update now() comment '更新时间'
) engine = innodb
  default charset = utf8mb4 comment '券';

-- 券活动
# drop table voucher_activity_info;
create table voucher_activity_info
(
    id             bigint unsigned primary key auto_increment not null comment 'id',
    title          varchar(64)   default ''                   not null comment '活动名称',
    dep_id         varchar(1024) default ''                   not null comment '所属组织',
    column_id      int           default 0                    not null comment '所属分类（栏目）',
    activity_type  tinyint       default 0                    not null comment '活动类型 1 券包, 2 兑换码, 3 通用口令',
    start_time     datetime      default now()                not null comment '活动开始时间',
    end_time       datetime      default now()                not null comment '活动结束时间',

    expand_json    json                                       null comment '扩展属性',

    operator       varchar(32)   default ''                   not null comment '操作人',
    enabled_status tinyint       default 0                    not null comment '启用状态 1 是 0 否',
    delete_status  tinyint       default 0                    not null comment '删除状态 1 是 0 否',
    create_time    datetime      default now()                not null comment '创建时间',
    update_time    datetime      default now()                not null on update now() comment '更新时间'
) engine = innodb
  default charset = utf8mb4 comment '券活动';

-- 券活动关联
# drop table voucher_activity_relation;
# create table voucher_activity_relation
    # (
          #     id           bigint unsigned primary key auto_increment not null comment 'id',
          #     activity_id  bigint default 0                           not null comment '活动id',
          #     voucher_id   bigint default 0                           not null comment '券id',
          #     voucher_code varchar(32)                                not null comment '券码',
    #     unique uniq_activity_id_voucher_id (activity_id, voucher_id)
    # ) engine = innodb
    #   default charset = utf8mb4 comment '卡券活动关联';


# drop table voucher_user;
create table voucher_user
(
    id            bigint unsigned primary key auto_increment not null comment 'id',
    voucher_id    bigint      default 0                      not null comment '券id',
    voucher_code  varchar(32) default ''                     not null comment '券码',
    user_id       bigint      default 0                      not null comment '用户id',
    use_status    tinyint     default 0                      not null comment '使用状态 0 未使用 1 已使用 2 已过期',
    expire_time   datetime    default now()                  not null comment '过期时间',

    delete_status tinyint     default 0                      not null comment '删除状态 1 是 0 否',
    create_time   datetime    default now()                  not null comment '创建时间',
    update_time   datetime    default now()                  not null on update now() comment '更新时间'
) engine = innodb
  default charset = utf8mb4 comment '用户优惠券';


select *
from voucher_user;


