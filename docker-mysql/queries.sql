select table_schema as database_name,
    table_name
from information_schema.tables
where table_type = 'BASE TABLE'
        and table_schema = 'spring_batch' -- enter your database name here
order by database_name, table_name;

delete from spring_batch.BATCH_STEP_EXECUTION_SEQ;
delete from spring_batch.BATCH_STEP_EXECUTION_CONTEXT;
delete from spring_batch.BATCH_STEP_EXECUTION;
delete from spring_batch.BATCH_JOB_SEQ;
delete from spring_batch.BATCH_JOB_EXECUTION_SEQ;
delete from spring_batch.BATCH_JOB_EXECUTION_CONTEXT;
delete from spring_batch.BATCH_JOB_EXECUTION_PARAMS;
delete from spring_batch.BATCH_JOB_EXECUTION;
delete from spring_batch.BATCH_JOB_INSTANCE;

select * from spring_batch.BATCH_STEP_EXECUTION_SEQ;
select * from spring_batch.BATCH_STEP_EXECUTION_CONTEXT;
select * from spring_batch.BATCH_STEP_EXECUTION;
select * from spring_batch.BATCH_JOB_SEQ;
select * from spring_batch.BATCH_JOB_EXECUTION_SEQ;
select * from spring_batch.BATCH_JOB_EXECUTION_CONTEXT;
select * from spring_batch.BATCH_JOB_EXECUTION_PARAMS;
select * from spring_batch.BATCH_JOB_EXECUTION;
select * from spring_batch.BATCH_JOB_INSTANCE;