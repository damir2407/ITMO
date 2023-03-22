DO
$$
    DECLARE
        row    RECORD;
        answer INT = 0;

    BEGIN
        FOR row IN
            (SELECT table_schema,
                    table_name,
                    column_name,
                    is_nullable
             FROM information_schema.columns
             WHERE table_schema = 's313086'
             AND is_nullable = 'NO'
             AND column_name NOT IN (SELECT information_schema.key_column_usage.column_name
                                       FROM information_schema.table_constraints
                                       JOIN information_schema.key_column_usage
                                       ON information_schema.key_column_usage.constraint_name =
                                                        information_schema.table_constraints.constraint_name
                                       WHERE information_schema.key_column_usage.table_name IN (SELECT table_name
                                                                                                FROM information_schema.columns
                                                                                                WHERE table_schema = 's313086')
                                       AND information_schema.table_constraints.constraint_type = 'PRIMARY KEY'))
            LOOP EXECUTE 'ALTER TABLE ' || row.table_schema || '.' || row.table_name ||
                        ' ALTER COLUMN ' || row.column_name || ' DROP NOT NULL;';
                answer = answer + 1;
            END LOOP;
        RAISE NOTICE 'Схема: s313086';
        RAISE NOTICE 'Ограничений целостности типа NOT NULL отключено: %', answer;
    END;
$$ LANGUAGE plpgsql;
