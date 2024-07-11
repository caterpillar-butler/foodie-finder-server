# JPA에서 사용되는 기능과 관련된 키워드

## 1. 기본 CRUD 작업
- **키워드**: `CrudRepository`, `JpaRepository`, `save`, `findById`, `findAll`, `deleteById`
- **설명**: Spring Data JPA의 리포지토리 인터페이스를 사용하여 기본 CRUD 작업을 수행한다.

## 2. 연관 관계 매핑
- **키워드**: `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`, `@JoinColumn`, `@JoinTable`, `CascadeType`, `FetchType`
- **설명**: 엔티티 간의 관계를 정의하여 연관된 데이터를 효율적으로 관리한다.

## 3. 자동 스키마 생성
- **키워드**: `hibernate.hbm2ddl.auto`, `ddl-auto`, `create`, `update`, `create-drop`
- **설명**: JPA 설정을 통해 애플리케이션 실행 시 데이터베이스 스키마를 자동으로 생성하거나 업데이트한다.

## 4. 트랜잭션 관리
- **키워드**: `@Transactional`, `Propagation`, `Isolation`, `rollbackFor`, `noRollbackFor`
- **설명**: 트랜잭션 경계를 설정하여 데이터 일관성을 유지하고, 데이터베이스 작업의 원자성을 보장한다.

# MyBatis에서 사용되는 기능과 관련된 키워드

## 1. 복잡한 SQL 쿼리
- **키워드**: `@Select`, `@Insert`, `@Update`, `@Delete`, `@SelectProvider`, `@InsertProvider`, `@UpdateProvider`, `@DeleteProvider`, `@Results`, `@Result`
- **설명**: 애노테이션이나 XML을 사용하여 복잡한 SQL 쿼리를 정의하고 실행한다.

## 2. 동적 SQL (XML)
- **키워드**: `<choose>`, `<when>`, `<otherwise>`, `<if>`, `<trim>`, `<where>`, `<set>`
- **설명**: XML 파일을 사용하여 조건에 따라 SQL 쿼리를 동적으로 생성한다. 복잡한 조건문이나 동적 쿼리를 작성할 때 유용한다.

## 3. 고급 매핑 설정 (XML)
- **키워드**: `<resultMap>`, `<association>`, `<collection>`
- **설명**: XML 파일에서 결과 매핑을 세밀하게 제어하여 복잡한 결과 매핑이나 커스텀 매핑을 수행한다.

## 4. 캐시 설정 (XML)
- **키워드**: `<cache>`, `<cache-ref>`
- **설명**: XML 파일을 사용하여 캐시의 범위, 만료 시간, 캐시 구현체 등을 설정하여 성능을 최적화한다.

## 5. 대량 데이터 처리
- **키워드**: `batch`, `ExecutorType.BATCH`, `SqlSession`, `insert`, `update`, `delete`, `flushStatements`
- **설명**: 배치 처리를 통해 대용량 데이터의 삽입, 업데이트, 삭제 작업을 효율적으로 수행한다.

## 6. 특정 성능 최적화
- **키워드**: `index hint`, `pagination`, `SQL tuning`, `query optimization`
- **설명**: 인덱스 힌트, 페이징, 쿼리 튜닝 등을 통해 SQL 쿼리 성능을 최적화한다.
