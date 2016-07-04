package ua.opinta;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.opinta.core.Role;
import ua.opinta.dao.RoleRepository;
import ua.opinta.service.RoleService;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Administrator on 7/2/2016.
 */
@Transactional
public class RoleServiceTest extends AbstractTest {

    @Autowired
    private RoleService service;

    @Mock
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service.evictCache();
        service.setRoleRepository(roleRepository);
    }

    @After
    public void tearDown() {
        // some logic after each test
    }

    @Test
    public void testFindAll() {
        service.findAll();

        verify(roleRepository, times(1)).findAll();
    }

    @Test
    public void testFindOne() {
        long id = anyLong();
        service.findOne(id);

        verify(roleRepository, times(1)).findOne(id);
    }

    @Test
    public void testCreate() {
        Role role = new Role(0l, "some new");
        service.create(role);

        verify(roleRepository, times(1)).save(role);
    }

    @Test
    public void testUpdate() {
        Role role = new Role(0l, "some new");
        service.update(role);

        verify(roleRepository, times(1)).findOne(role.getId());
        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    public void testDelete() {
        service.delete(anyLong());

        verify(roleRepository, times(1)).delete(anyLong());
    }
}
