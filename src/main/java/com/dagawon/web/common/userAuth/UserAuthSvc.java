package com.dagawon.web.common.userAuth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserAuthSvc {
/*

    private final TbCustMasterRepository custMasterRepo;

    @Transactional
    public UserDetails getUserDetails(String membNo) {

        // 승인된 사용자만 통과시키기(statCd)
        TbCustMasterDto membersDto = TbCustMasterMapper.INSTANCE.toDto(custMasterRepo.findByMembNo(membNo).orElseThrow(
                () -> new NotFoundException("고객 정보가 존재하지 않습니다!")
        ));
        return UserPrincipal.create(membersDto);
    }

    @Transactional
    public UserDetails getAdminDetails(String lognId) {

        TbCustMasterDto membersDto = TbCustMasterMapper.INSTANCE.toDto(custMasterRepo.findByMembNo("").orElseThrow(
                () -> new NotFoundException("### 삭제,수정 필요 ###")
        ));
        return UserPrincipal.create(membersDto);
    }

    */
/**
     * 로그인한 사람 정보 조회
     *
     * @return : UserPrincipal 객체 (회원번호, 회원명 등 포함)
     *//*

    public UserPrincipal getSessionUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null || Objects.isNull(principal)) {
            throw new NotFoundException("JWT Session 정보가 존재하지 않습니다. 다시 로그인 해주세요!");
        }

        // principal이 UserPrincipal 타입인지 확인하고, 아니면 예외를 던짐
        if (!(principal instanceof UserPrincipal)) {
            throw new ClassCastException("principal이 UserPrincipal이 아닌 타입으로 반환됨: " + principal.getClass().getName());
        }

        return (UserPrincipal) principal;
    }

    */
/**
     * Static 메서드를 사용하여 세션 정보 조회
     *
     * @return : UserPrincipal 객체 (회원번호, 회원명 등 포함)
     *//*

    public static UserPrincipal getStaticSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            throw new NotFoundException("JWT Session 정보가 존재하지 않습니다. 다시 로그인 해주세요!");
        }

        // principal의 실제 타입을 출력하여 확인 (디버깅 용도)
        System.out.println("Principal Type: " + principal.getClass().getName());

        // principal이 UserPrincipal 타입인지 확인하고, 아니면 예외를 던짐
        if (!(principal instanceof UserPrincipal)) {
            throw new ClassCastException("principal이 UserPrincipal이 아닌 타입으로 반환됨: " + principal.getClass().getName());
        }

        return (UserPrincipal) principal;
    }
*/
/*

    */
/**
     * 현재 세션의 회원 아이디 반환
     *
     * @return : 회원 아이디 (loginId)
     *//*

    public static String getLoginId() {
        return getStaticSession().getId();
    }

    */
/**
     * 현재 세션의 회원 번호 반환
     *
     * @return : 회원 번호 (membNo)
     *//*

    public static String getMembNo() {
        return getStaticSession().getMembNo();
    }

    */
/**
     * 현재 세션의 회원 이름 반환
     *
     * @return : 회원 이름 (membNm)
     *//*

    public static String getMemberNm() {
        return getStaticSession().getUsername();
    }

    */
/**
     * 현재 세션 회원 사업자 번호 반환
     *
     * @return : 사업자 번호 (bizNo)
     *//*

    public static String getBizNo() {
        return getStaticSession().getBizNo();
    }

*/

}
